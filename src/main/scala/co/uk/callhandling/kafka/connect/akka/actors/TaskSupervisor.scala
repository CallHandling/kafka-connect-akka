package co.uk.callhandling.kafka.connect.akka.actors

import java.util.UUID

import akka.actor.{Actor, ActorLogging, ActorRef, Props}
import akka.cluster.pubsub.DistributedPubSub
import akka.cluster.pubsub.DistributedPubSubMediator.{Publish, Subscribe}
import co.uk.callhandling.kafka.connect.akka.CreateWorker

/**
  * Created by yeshwanth on 27/03/17.
  */

//
// Ideally, there should be a pool of actors as workers.
//


case class Pub(msg: String)

case class Msg(s: String)


object TaskSupervisor {
  def props(): Props = Props(new TaskSupervisor())
}

/**
  * TaskSupervisor is the supervisor actor for a specific task
  * (Must have a set of worker actors for publishing or one actor per task for publishing)
  */
class TaskSupervisor() extends Actor with ActorLogging {
  implicit val system = context.system
  var worker: Option[ActorRef] = None


  override def receive: Receive = {
    case CreateWorker =>
      system.actorOf(Props[Worker], "testworker")
    //  case Publish(message) =>
  }
}


/**
  * This is a publisher actor which publishes
  * to a topic
  */
class Worker extends Actor {

  val mediator = DistributedPubSub(context.system).mediator

  val topic = "randomtopic" //ToDo: Ugly! get it from configDef
                            // This needs to be configurable according to the
                            // number of tasks in taskConfig

  override def receive: Receive = {
    case Pub(message) => mediator ! Publish(topic, message)
  }

}


///////subscriber for test purposes
class Subs extends Actor {

  val mediator = DistributedPubSub(context.system).mediator
  val topic = "testtopic"
  mediator ! Subscribe(topic, self)

  override def receive = {
    case x: Any =>
      println(x)
  }
}



