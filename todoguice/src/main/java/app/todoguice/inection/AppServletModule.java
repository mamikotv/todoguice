package app.todoguice.inection;

import java.util.EnumSet;
import java.util.Map;
import javax.inject.Named;
import javax.inject.Singleton;
import javax.servlet.DispatcherType;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import com.google.common.collect.ImmutableMap;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.servlet.GuiceFilter;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import app.todoguice.servlet.IndexServlet;
import freemarker.ext.servlet.FreemarkerServlet;

public class AppServletModule extends ServletModule {

  @Override
  protected void configureServlets() {
    install(new ConfigModule());
    install(new PersistenceModule());

    bind(GuiceFilter.class).in(Singleton.class);
    bind(FreemarkerServlet.class).in(Singleton.class);

    serve("/").with(IndexServlet.class);
    Map<String, String> fmInitParam = ImmutableMap.<String, String>builder()
        .put("TemplatePath", "classpath:template")
        .put("NoCache", "true")
        .put("ResponseCharacterEncoding", "fromTemplate")
        .put("ExceptionOnMissingTemplate", "true")
        .put("incompatible_improvements", "2.3.31")
        .put("template_exception_handler", "rethrow")
        .put("template_update_delay", "30 s")
        .put("default_encoding", "UTF-8")
        .put("output_encoding", "UTF-8")
        .put("locale", "ja_JP")
        .put("number_format", "0.##########")
        .build();
    serve("*.ftlh").with(FreemarkerServlet.class, fmInitParam);
  }

  @Provides
  @Singleton
  public Server provideServer(@Named("server.port") Integer port,
      GuiceFilter guiceFilter,
      GuiceServletContextListener guiceServletContextListener) {
    Server server = new Server(port);
    
    final ServletContextHandler context = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);

    final FilterHolder guiceFilterHolder = new FilterHolder(guiceFilter);
    context.addFilter(guiceFilterHolder, "/*", EnumSet.allOf(DispatcherType.class));
    context.addEventListener(guiceServletContextListener);

    return server;
  }

  @Provides
  @Singleton
  public GuiceServletContextListener provideContextListener(final Injector injector) {
    return new GuiceServletContextListener() {
      @Override
      protected Injector getInjector() {
        return injector;
      }
    };
  }
}
