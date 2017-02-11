package com.github.laysakura.sample4qiita.modules

import com.github.laysakura.sample4qiita.annotations.VerboseServiceServer
import com.google.inject.{Provides, Singleton}
import com.twitter.inject.TwitterModule

object VerboseServerModule extends TwitterModule {

  @Singleton
  @Provides
  @VerboseServiceServer
  def provide: String = "localhost:4000"
}
