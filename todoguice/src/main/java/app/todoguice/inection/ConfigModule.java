package app.todoguice.inection;

import org.eclipse.microprofile.config.ConfigProvider;
import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class ConfigModule extends AbstractModule {

  @Override
  protected void configure() {
    // MicroProfile Config
    ConfigProvider.getConfig()
        .getConfigSources()
        .forEach(config -> Names.bindProperties(binder(), config.getProperties()));
    
    // BeanValidation
    bind(Validator.class).toInstance(Validation.buildDefaultValidatorFactory().getValidator());
  }
}
