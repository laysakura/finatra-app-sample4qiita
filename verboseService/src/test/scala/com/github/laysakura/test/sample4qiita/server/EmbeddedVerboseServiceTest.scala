package com.github.laysakura.test.sample4qiita.server

import com.github.laysakura.sample4qiita.idl.VerboseService
import com.github.laysakura.sample4qiita.modules.{ClientIdModule, VerboseServerModule, VerboseServiceModule}
import com.github.laysakura.sample4qiita.server.VerboseServiceServer
import com.twitter.finatra.thrift.{EmbeddedThriftServer, ThriftClient}
import com.twitter.inject.app.TestInjector
import com.twitter.inject.server.FeatureTest
import com.twitter.util.{Await, Future}

class EmbeddedVerboseServiceTest extends FeatureTest {
  override val injector = TestInjector(
    modules = Seq(
      new ClientIdModule("VerboseServiceTest"),
      VerboseServiceModule,
      VerboseServerModule
    )
  )

  override val server = new EmbeddedThriftServer(
    twitterServer = new VerboseServiceServer
  ) with ThriftClient

  lazy private val client = server.thriftClient[VerboseService[Future]]("EmbeddedVerboseServiceTest")

  "client" should {
    "successfully call echo() API" in {
      val res = Await.result(client.echo("hi :D"))
      res shouldBe "You said: hi :D"
    }
  }
}
