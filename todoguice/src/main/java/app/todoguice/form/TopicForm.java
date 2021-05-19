package app.todoguice.form;

import com.google.common.base.MoreObjects;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TopicForm {
  /** タイトル */
  @NotBlank
  @Size(max = 64)
  private final String title;
  /** 本文 */
  @NotBlank
  @Size(max = 1024)
  private final String body;
  /** 期限 */
  @NotBlank
  @Size(max = 10)
  private final String limit;

  public String getTitle() {
    return title;
  }

  public String getBody() {
    return body;
  }

  public String getLimit() {
    return limit;
  }

  public TopicForm(String title, String body, String limit) {
    this.title = title;
    this.body = body;
    this.limit = limit;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).toString();
  }
}
