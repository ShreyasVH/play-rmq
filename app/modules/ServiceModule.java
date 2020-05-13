package modules;

import com.google.inject.AbstractModule;
import services.QueueService;
import services.impl.QueueServiceImpl;

public class ServiceModule extends AbstractModule
{
    @Override
    protected void configure()
    {
        bind(QueueService.class).to(QueueServiceImpl.class).asEagerSingleton();
    }
}
