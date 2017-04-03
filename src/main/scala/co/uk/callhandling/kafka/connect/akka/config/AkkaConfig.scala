package co.uk.callhandling.kafka.connect.akka.config

import org.apache.kafka.common.config.{AbstractConfig, ConfigDef}
import java.util.{Map => JMap}
import java.util

import org.apache.kafka.common.config.ConfigDef.{Importance, Type}

/**
  * Created by yeshwanth on 27/03/17.
  */


object AkkaConfigSink {


  val sinkConfig: ConfigDef = new ConfigDef()
    .define(AkkaConfigConstants.ACTOR_SYSTEM_NAME, Type.STRING, Importance.HIGH, AkkaConfigConstants.DESC, "Mappings", 1, ConfigDef.Width.LONG, AkkaConfigConstants.DISPLAY_NAME)

  //define all akka configuration here
}


case class AkkaConfigSink(props: JMap[String, String]) extends AbstractConfig(AkkaConfigSink.sinkConfig, props) {}


object AkkaConfigConstants {

  val ACTOR_SYSTEM_NAME = "actor.system.name"
  val DISPLAY_NAME = "connect.akka.sink"
  val DESC = "Actor system for the connector"


}