package co.uk.callhandling.kafka.connect.akka

import java.util

import org.apache.kafka.connect.connector.{ConnectorContext, Task}
import org.apache.kafka.connect.sink.SinkConnector
import java.util.{List => JList, Map => JMap}

import akka.actor.{ActorRef, ActorSystem, Props}
import akka.cluster.Cluster
import co.uk.callhandling.kafka.connect.akka.AkkaUtil.actorSystem
import co.uk.callhandling.kafka.connect.akka.actors.{Subs, TaskSupervisor}
import co.uk.callhandling.kafka.connect.akka.config.AkkaConfigSink
import org.apache.kafka.common.config.ConfigDef

import scala.collection.JavaConversions._


/**
  * Created by yeshwanth on 23/03/17.
  */


class AkkaSink extends SinkConnector {

  private var configProps: JMap[String, String] = _
  //option
  private val configDef: ConfigDef = AkkaConfigSink.sinkConfig
  private var taskSupervisor: Option[ActorRef] = None
  private var connector_name: String = ""


  /**
    * Defines which sink class to use
    */
  override val taskClass: Class[_ <: Task] = classOf[AkkaSinkTask]


  override def initialize(ctx: ConnectorContext): Unit = {

  }

  override def start(props: JMap[String, String]): Unit = {

    /**
      * Actor system for the connector
      */
    AkkaUtil.createActorSystem(props.get("actor.system.name"))


    connector_name = props.get("name")
    configProps = props
  }

  /**
    * For parallelism of tasks
    * Creates actor per task depending on tasks.max
    */
  override def taskConfigs(tasks: Int): util.List[util.Map[String, String]] = {
    //ToDo: Look for a way to get taskId
    (0 to tasks - 1).map(task =>
      config(task)
    ).toList
  }

  def config(t: Int): util.Map[String, String] = {
    //  taskSupervisor.get ! Worker(getTaskId(t))
    configProps
  }


  /**
    * taskId will be the worker actor name
    */
  def getTaskId(t: Int): String = {
    connector_name + "-" + t.toString()
  }

  override def stop(): Unit = {}

  override def version(): String = "1"

  override def config(): ConfigDef = configDef

}

/**
  * Singleton akka utility
  */
object AkkaUtil {

  var actorSystem = ActorSystem()

  def createActorSystem(name: String): Unit = {
    actorSystem = ActorSystem(name)


    ////////test purposes only//////
    val joinAddress = Cluster(actorSystem).selfAddress
    Cluster(actorSystem).join(joinAddress)

    val system2 = ActorSystem(name)
    Cluster(system2).join(joinAddress)
    system2.actorOf(Props[Subs], "subscriber")
    //////////////////////////////
  }
}

