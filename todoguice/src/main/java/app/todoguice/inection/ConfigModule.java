package app.todoguice.inection;

import org.eclipse.microprofile.config.ConfigProvider;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

public class ConfigModule extends AbstractModule {

  @Override
  protected void configure() {
    ConfigProvider.getConfig()
        .getConfigSources()
        .forEach(config -> Names.bindProperties(binder(), config.getProperties()));
  }
}
