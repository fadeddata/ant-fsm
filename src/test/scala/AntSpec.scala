import akka.testkit._
import org.scalatest._

class AntSpec extends FSMSpec {
  val ant = TestFSMRef(new Ant())

  "Ant" should {
    "be FindingLeaf with NoLeaf initially" in {
      ant.stateData should be(NoLeaf)
      ant.stateName should be(FindingLeaf)
    }

    "be GoingHome with HasLeaf after FoundLeaf" in {
      ant ! FoundLeaf
      ant.stateData should be(HasLeaf)
      ant.stateName should be(GoingHome)
    }

    "be FindingLeaf with NoLeaf after ArrivedHome" in {
      ant ! ArrivedHome
      ant.stateData should be(NoLeaf)
      ant.stateName should be(FindingLeaf)
    }

    "be RunningAway after EnemyNear" in {
      ant ! EnemyNear
      ant.stateData should be(NoLeaf)
      ant.stateName should be(RunningAway)
    }

    "be FindingLeaf after EnemyDistant" in {
      ant ! EnemyDistant
      ant.stateData should be(NoLeaf)
      ant.stateName should be(FindingLeaf)
    }

    "be RunningAway with HasLeaf after FoundLeaf and EnemyNear" in {
      ant ! FoundLeaf
      ant ! EnemyNear
      ant.stateData should be(HasLeaf)
      ant.stateName should be(RunningAway)
    }

    "be GoingHome with HasLeaf after EnemyDistant" in {
      ant ! EnemyDistant
      ant.stateData should be(HasLeaf)
      ant.stateName should be(GoingHome)
    }
  }
}
