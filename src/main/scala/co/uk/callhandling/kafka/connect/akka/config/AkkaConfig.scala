package co.uk.callhandling.kafka.connect.akka.config

import org.apache.kafka.common.config.{AbstractConfig, ConfigDef}
import java.util.{Map => JMap}

/**
  * Created by yeshwanth on 27/03/17.
  */



object AkkaConfigSink {

  val sinkConfig: ConfigDef = new ConfigDef()
  //define all akka configuration here
}

case class AkkaConfigSink(props: JMap[String, String]) extends AbstractConfig(AkkaConfigSink.sinkConfig, props) {}
