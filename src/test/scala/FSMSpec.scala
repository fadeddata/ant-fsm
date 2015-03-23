import akka.actor.ActorSystem
import akka.testkit._
import org.scalatest._

abstract class FSMSpec extends TestKit(ActorSystem()) with ImplicitSender
  with WordSpecLike with ShouldMatchers with BeforeAndAfterAll {
  override def afterAll = TestKit.shutdownActorSystem(system)
}