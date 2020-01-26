package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._


class HelloControllerSpec extends PlaySpec with GuiceOneAppPerTest with Injecting {
  "get" should {
    "nominal scenario" in {
      val name = "hoge"
      val request = FakeRequest(GET, s"/hello?name=$name")
      val response = route(app, request).get

      status(response) mustBe OK
      contentAsString(response) mustBe s"Hello, $name"
    }

    "non-nominal scenario" in {
      val request = FakeRequest(GET, "/hello")
      val response = route(app, request).get

      contentAsString(response) mustBe """Please give a name as a query parameter named "name"."""
    }
  }
}
