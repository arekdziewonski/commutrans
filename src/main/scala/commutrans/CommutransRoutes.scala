package commutrans

import java.util.concurrent.Executors

import cats.effect.{ContextShift, Effect, IO, Sync}
import cats.implicits._
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl
import org.http4s.server.staticcontent
import org.http4s.server.staticcontent.WebjarService.Config

import scala.concurrent.ExecutionContext

object CommutransRoutes {

  def jokeRoutes[F[_]: Sync](J: Jokes[F]): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F]{}
    import dsl._
    HttpRoutes.of[F] {
      case GET -> Root / "joke" =>
        for {
          joke <- J.get
          resp <- Ok(joke)
        } yield resp
    }
  }

  def helloWorldRoutes[F[_]: Sync](H: HelloWorld[F]): HttpRoutes[F] = {
    val dsl = new Http4sDsl[F]{}
    import dsl._
    HttpRoutes.of[F] {
      case GET -> Root / "hello" / name =>
        for {
          greeting <- H.hello(HelloWorld.Name(name))
          resp <- Ok(greeting)
        } yield resp
    }
  }

  val webjars: HttpRoutes[IO] = {
    val blockingEc = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(4))
    implicit val cs: ContextShift[IO] = IO.contextShift(ExecutionContext.global)
    staticcontent.webjarService(Config(blockingEc))
  }
}