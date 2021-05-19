package app.todoguice.service;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import app.todoguice.exception.ValidateException;
import app.todoguice.form.TopicForm;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

@Singleton
public class PostService {
  private final Validator validator;

  public void save(TopicForm form) throws ValidateException {
    // バリデーション
    validateForm(form);
  }
  
  protected void validateForm(TopicForm form) throws ValidateException {
    Set<ConstraintViolation<TopicForm>> validate = validator.validate(form);
    if (validate.isEmpty()) {
      return;
    }

    Map<String, Collection<String>> messages = validate.stream()
        .collect(Collectors.groupingBy(c -> c.getPropertyPath().toString(),
            Collectors.mapping(ConstraintViolation::getMessage, Collectors.toCollection(LinkedHashSet::new))));
    throw new ValidateException(messages);
  }

  @Inject
  public PostService(Validator validator) {
    this.validator = validator;
  }
}
