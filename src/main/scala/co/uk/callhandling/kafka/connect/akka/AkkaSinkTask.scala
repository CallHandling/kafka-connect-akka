package co.uk.callhandling.kafka.connect.akka

import org.apache.kafka.connect.sink.{SinkRecord, SinkTask}
import org.apache.kafka.clients.consumer.OffsetAndMetadata
import java.util.{Collection => JCollection, Map => JMap}

import akka.actor.{ActorRef, ActorSystem, Props}
import co.uk.callhandling.kafka.connect.akka.actors.{Pub, Subs, TaskSupervisor}
import org.apache.kafka.common.TopicPartition
import akka.cluster.Cluster


/**
  * Created by yeshwanth on 23/03/17.
  */

case class CreateWorker()


class AkkaSinkTask extends SinkTask {

  import AkkaUtil._

  private var taskSupervisor: Option[ActorRef] = None

  /**
    *
    * @param props
    */
  override def start(props: JMap[String, String]): Unit = {
    //start the worker actor with the same taskname


    /**
      * Supervisor for a specific task. Can have multiple workers
      */
    taskSupervisor = Some(actorSystem.actorOf(TaskSupervisor.props(), "taskSupervisor"))
    taskSupervisor.get ! CreateWorker()

  }

  /**
    *
    * @param records
    */
  override def put(records: JCollection[SinkRecord]): Unit = {

    //  ActorPool.write(records, taskSupervisor)
  }

  override def flush(offsets: JMap[TopicPartition, OffsetAndMetadata]): Unit = {}

  override def stop(): Unit = {}

  override def version(): String = "1"

}


object ActorPool {

  def write(records: JCollection[SinkRecord], supervisor: Option[ActorRef]) = {
    //ToDO
    //Parse SinkRecords
    //
    supervisor.get ! Pub("test")

  }

}