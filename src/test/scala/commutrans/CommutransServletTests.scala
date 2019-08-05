package commutrans

import org.scalatra.test.scalatest._

class CommutransServletTests extends ScalatraFunSuite {

  addServlet(classOf[CommutransServlet], "/*")

  test("GET / on CommuTransServlet should return status 200") {
    get("/") {
      status should equal (200)
    }
  }

}
