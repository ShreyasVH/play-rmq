package services;

public interface QueueService
{
    Boolean publish(String exchange, String message, String routingKey) throws Exception;

    void consume(String exchangeName, String queueName, String routingKey) throws Exception;
}
