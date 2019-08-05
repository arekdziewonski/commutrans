package commutrans

import java.io.{ByteArrayOutputStream, InputStream, OutputStream}

import org.scalatra._

class StaticResourceServlet extends ScalatraServlet {

  get("*") {
    val splat = params("splat")
    val resourcePath = "/META-INF/resources/webjars" + (if (!splat.startsWith("/")) "/" else "") + splat
    Option(getClass.getResourceAsStream(resourcePath)) match {
      case Some(inputStream) => {
        contentType = servletContext.getMimeType(resourcePath)
        loadBytesFrom(inputStream)
      }
      case None => resourceNotFound()
    }
  }

  private def loadBytesFrom(in: InputStream): Array[Byte] = {
    val baos = new ByteArrayOutputStream
    try {
      copy(in, baos)
    } finally {
      in.close
    }
    baos.toByteArray
  }

  private def copy(in: InputStream, out: OutputStream): Long = {
    var bytesCopied: Long = 0
    val buffer = new Array[Byte](8192)

    var bytes = in.read(buffer)
    while (bytes >= 0) {
      out.write(buffer, 0, bytes)
      bytesCopied += bytes
      bytes = in.read(buffer)
    }

    bytesCopied
  }
}
