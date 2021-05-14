package app.todoguice.inection;

import javax.inject.Named;
import javax.inject.Singleton;
import javax.sql.DataSource;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DefaultDSLContext;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class PersistenceModule extends AbstractModule {

  @Provides
  @Singleton
  public DSLContext provideDSLContext(DataSource ds) {
    return new DefaultDSLContext(ds, SQLDialect.POSTGRES);
  }
  
  @Provides
  @Singleton
  public DataSource provideDataSource(@Named("jdbc.url") String jdbcUrl,
      @Named("jdbc.username") String username, 
      @Named("jdbc.password") String password) {
    HikariConfig config = new HikariConfig();
    config.setJdbcUrl(jdbcUrl);
    config.setUsername(username);
    config.setPassword(password);
    return new HikariDataSource(config);
  }
}
