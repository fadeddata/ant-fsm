import akka.actor._

sealed trait AntMessage
case object EnemyNear extends AntMessage
case object EnemyDistant extends AntMessage
case object FoundLeaf extends AntMessage
case object ArrivedHome extends AntMessage

sealed trait State
case object RunningAway extends State
case object GoingHome extends State
case object FindingLeaf extends State

sealed trait Data
case object HasLeaf extends Data
case object NoLeaf extends Data

class Ant extends Actor with FSM[State, Data] {
  startWith(FindingLeaf, NoLeaf)

  when(FindingLeaf) {
    case Event(FoundLeaf, NoLeaf) =>
      goto(GoingHome) using(HasLeaf)
    case Event(EnemyNear, _) =>
      goto(RunningAway)
  }

  when(GoingHome) {
    case Event(ArrivedHome, HasLeaf) =>
      goto(FindingLeaf) using(NoLeaf)
    case Event(EnemyNear, _) =>
      goto(RunningAway)
  }

  when(RunningAway) {
    case Event(EnemyDistant, NoLeaf) =>
      goto(FindingLeaf)
    case Event(EnemyDistant, HasLeaf) =>
      goto(GoingHome)
  }
}
