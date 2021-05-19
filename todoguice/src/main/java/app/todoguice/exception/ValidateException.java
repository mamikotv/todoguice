package app.todoguice.exception;

import java.util.Collection;
import java.util.Map;

public class ValidateException extends RuntimeException {

  private static final long serialVersionUID = -1L;

  private final Map<String, Collection<String>> messages;

  public ValidateException(Map<String, Collection<String>> messages) {
    this.messages = messages;
  }

  public Map<String, Collection<String>> getMessages() {
    return messages;
  }
}
