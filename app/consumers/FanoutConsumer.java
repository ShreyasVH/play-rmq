package consumers;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FanoutConsumer extends DefaultConsumer
{
    private String name;
    public FanoutConsumer(Channel channel, Long num) throws IOException
    {
        super(channel);
        this.name = "Consumer-" + num;
        channel.basicQos(2);
    }

    @Override
    public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException
    {
        System.out.println(this.name);
        System.out.println(new String(body, StandardCharsets.UTF_8));

        long deliveryTag = envelope.getDeliveryTag();

        this.getChannel().basicAck(deliveryTag, false);
    }
}
