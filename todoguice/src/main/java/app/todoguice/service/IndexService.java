package app.todoguice.service;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Singleton
public class IndexService {

  private final String appTitle;
  
  public String getTitle() {
    return this.appTitle;
  }

  @Inject
  public IndexService(@Named("app.title") String appTitle) {
    this.appTitle = appTitle;
  }
}
