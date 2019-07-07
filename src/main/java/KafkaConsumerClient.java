import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.log4j.Logger;

import java.util.Properties;

public class KafkaConsumerClient {

    private final static Logger LOGGER = Logger.getLogger(KafkaConsumerClient.class);

    public KafkaConsumer consumer = null;

    public KafkaConsumerClient(String host, String clientId, String groupId) {
        consumer = createKafkaConsumer(host, clientId, groupId);
    }

    private KafkaConsumer createKafkaConsumer(final String host, final String clientId, final String groupId) {

        LOGGER.info("creating kafka consumer for host: " + host + ", clientId: " + clientId);
        final Properties props = new Properties();

        props.put(ConsumerConfig.CLIENT_ID_CONFIG, clientId);
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, host);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);


        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        return new KafkaConsumer<>(props);
    }
}
