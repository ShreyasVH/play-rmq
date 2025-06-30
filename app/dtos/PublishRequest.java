package dtos;

import java.util.Map;

public class PublishRequest
{
    private String exchange;
    private String key;
    private Map<String, Object> payload;

    public String getExchange()
    {
        return this.exchange;
    }

    public String getKey()
    {
        return this.key;
    }

    public Map<String, Object> getPayload()
    {
        return this.payload;
    }
}
