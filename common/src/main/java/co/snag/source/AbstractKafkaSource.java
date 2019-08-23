package co.snag.source;

import org.graalvm.compiler.core.common.type.ArithmeticOpTable;

import java.util.Properties;

import static java.util.Objects.requireNonNull;

public class AbstractKafkaSource implements KafkaSource {
    private final Properties kafkaProperties;
    private final String kafkaTopic;
    private final String kafkaPartitionKey;
    private final String groupId;
    private final String sourceName;

    public AbstractKafkaSource(final Properties kafkaProperties, final String kafkaTopic, final String kafkaPartitionKey,
                               final String groupId, final String sourceName) {
        this.kafkaProperties = requireNonNull(kafkaProperties);
        this.kafkaTopic = requireNonNull(kafkaTopic);
        this.kafkaPartitionKey = kafkaPartitionKey;
        this.groupId = requireNonNull(groupId);
        this.sourceName = requireNonNull(sourceName);
    }

    @Override
    public String getSourceTopic() {
        return kafkaTopic;
    }

    @Override
    public String getConsumerGroupId() {
        return groupId;
    }

    @Override
    public Properties getSourceProperties() {
        return kafkaProperties;
    }

    @Override
    public String getName() {
        return sourceName;
    }
}
