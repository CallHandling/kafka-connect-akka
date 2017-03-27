package co.uk.callhandling.kafka.connect.akka

import org.apache.kafka.connect.connector.{ConnectorContext, Task}
import org.apache.kafka.connect.sink.SinkConnector
import java.util.{List => JList, Map => JMap}

import co.uk.callhandling.kafka.connect.akka.config.AkkaConfigSink
import org.apache.kafka.common.config.ConfigDef



/**
  * Created by yeshwanth on 23/03/17.
  */



class AkkaSink extends SinkConnector {

  private var configProps: JMap[String, String] = _
  private val configDef: ConfigDef = AkkaConfigSink.sinkConfig


  /**
   * Defines which sink class to use
   */
  override val taskClass: Class[_ <: Task] = classOf[AkkaSinkTask]


  //override def initialize(ctx: ConnectorContext): Unit = ()

  override def start(props: JMap[String, String]): Unit = {
    //start actor system for connection
  }

  /**
   * For parallelism of tasks
   */
  override def taskConfigs(tasks: Int): JList[JMap[String, String]] = {

    val configs = new java.util.ArrayList[java.util.Map[String, String]]()
    configs
   // (1 to tasks).map(_ => configProps.get).toList.asJava

  }


  override def stop(): Unit = {}

  override def version(): String = ""

  override def config(): ConfigDef = configDef

}
