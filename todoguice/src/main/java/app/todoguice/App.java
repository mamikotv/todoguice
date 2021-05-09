package app.todoguice;

import org.eclipse.jetty.server.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.inject.Guice;
import com.google.inject.Injector;
import app.todoguice.inection.AppServletModule;

/** App Main */
public class App {
  
  private static final Logger log = LoggerFactory.getLogger(App.class);
  
  public static void main(String[] args) {
    Injector injector = Guice.createInjector(new AppServletModule());
    Server server = injector.getInstance(Server.class);
    
    try {
      server.start();
    } catch (Exception e) {
      log.warn(e.getMessage(), e);
      try {
        server.stop();
      } catch (Exception e2) {
        log.warn(e2.getMessage(), e2);
      }
    }
  }
}
