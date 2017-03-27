package co.uk.callhandling.kafka.connect.akka

import org.apache.kafka.connect.sink.{SinkRecord, SinkTask}
import org.apache.kafka.clients.consumer.OffsetAndMetadata

import java.util.{Collection => JCollection, Map => JMap}

import org.apache.kafka.common.TopicPartition //java

/**
  * Created by yeshwanth on 23/03/17.
  */
class AkkaSinkTask extends SinkTask {

override def start(props: JMap[String, String]): Unit = {

  //create worker actor and mediator


}

override def put(records: JCollection[SinkRecord]): Unit = {

  //bang it to the mediator

}

override def flush(offsets: JMap[TopicPartition, OffsetAndMetadata]): Unit = {}

override def stop(): Unit = {}

  override def version(): String = "1"


}
