package modules;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.google.inject.AbstractModule;
import services.QueueService;
import services.impl.QueueServiceImpl;

@Singleton
public class StartModule extends AbstractModule
{
    private final QueueService queueService;

    @Inject
    public StartModule() throws Exception
    {
        this.queueService = new QueueServiceImpl();
    }

    @Override
    protected void configure()
    {
        try
        {
            this.startConsumers();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }

    }

    private void startConsumers() throws Exception
    {
        this.queueService.consume("fanout", "fanout", "");
        this.queueService.consume("fanout", "fanout", "");
    }
}
