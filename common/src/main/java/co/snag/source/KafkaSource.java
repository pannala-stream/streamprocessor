package co.snag.source;

public interface KafkaSource extends Source {
    String getSourceTopic();

    String getConsumerGroupId();
}
