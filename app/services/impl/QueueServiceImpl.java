package services.impl;

import com.rabbitmq.client.*;
import consumers.FanoutConsumer;
import services.QueueService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class QueueServiceImpl implements QueueService
{

    private static Connection connection = null;
    private static Long count = 0L;

    public QueueServiceImpl() throws Exception
    {
        if(null == connection)
        {
            ConnectionFactory factory = this.getConnectionFactory();
            connection = factory.newConnection();
        }
    }

    private ConnectionFactory getConnectionFactory()
    {
        ConnectionFactory factory = new ConnectionFactory();

        factory.setUsername(System.getenv("RMQ_USERNAME"));
        factory.setPassword(System.getenv("RMQ_USERNAME"));
        factory.setVirtualHost(System.getenv("RMQ_VHOSTS"));
        factory.setHost(System.getenv("RMQ_IP"));
        factory.setPort(Integer.parseInt(System.getenv("RMQ_PORT")));

        return factory;
    }

    private String getExchangeType(String exchange)
    {
        Map<String, BuiltinExchangeType> exchangeMap = new HashMap<String, BuiltinExchangeType>(){
            {
                put("fanout", BuiltinExchangeType.FANOUT);
                put("direct", BuiltinExchangeType.DIRECT);
            }
        };

        String type = "";
        if(exchangeMap.containsKey(exchange))
        {
            type = exchangeMap.get(exchange).getType();
        }

        return type;
    }

    private DefaultConsumer getConsumer(String exchangeName, Channel channel) throws IOException
    {
        DefaultConsumer consumer = null;
        switch(exchangeName)
        {
            case "fanout":
                consumer = new FanoutConsumer(channel, ++count);
                break;
        }
        return consumer;
    }

    @Override
    public Boolean publish(String exchange, String message, String routingKey) throws Exception
    {
        Boolean success = false;

        Channel channel = connection.createChannel();
        try
        {
//            channel.exchangeDeclare(exchange, this.getExchangeType(exchange), true);

            channel.basicPublish(exchange, routingKey, null, message.getBytes(StandardCharsets.UTF_8));
            success = true;
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

        return success;
    }

    @Override
    public void consume(String exchangeName, String queueName, String routingKey) throws Exception
    {
        Channel channel = connection.createChannel();

        try
        {
            channel.exchangeDeclare(exchangeName, this.getExchangeType(exchangeName), true);

            channel.queueDeclare(queueName, true, false, false, null);
            channel.queueBind(queueName, exchangeName, routingKey);

            channel.basicConsume(queueName, false, this.getConsumer(exchangeName, channel));
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
