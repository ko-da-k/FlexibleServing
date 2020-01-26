package modules

import actors.HelloActor
import com.google.inject.AbstractModule
import play.api.libs.concurrent.AkkaGuiceSupport

class MyModule extends AbstractModule with AkkaGuiceSupport {

  override def configure = {
    // set HelloActor
    bindActor[HelloActor]("configured-actor")
  }
}
