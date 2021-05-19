package app.todoguice.service;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Collection;
import java.util.Map;
import org.junit.jupiter.api.Test;
import app.todoguice.exception.ValidateException;
import app.todoguice.form.TopicForm;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class PostServiceTest {

  @Test
  void testValidateForm() {
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    PostService service = new PostService(validator);

    TopicForm form = new TopicForm("hello", "Thank you.", "2021/12/31");
    service.validateForm(form);
  }

  @Test
  void testValidateFormNullTitle() {
    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    PostService service = new PostService(validator);

    TopicForm form = new TopicForm(null, "Thank you.", "2021/12/31");
    try {
      service.validateForm(form);
      fail();
    } catch (ValidateException e) {
      Map<String, Collection<String>> messages = e.getMessages();
      assertNotNull(messages);
    }
  }

}
