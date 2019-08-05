package commutrans

import org.scalatra._

class CommutransServlet extends ScalatraServlet {

  get("/") {
    views.html.hello()
  }
}
