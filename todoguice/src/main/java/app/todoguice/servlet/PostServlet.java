package app.todoguice.servlet;

import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import app.todoguice.form.TopicForm;
import app.todoguice.service.PostService;

@Singleton
public class PostServlet extends HttpServlet {

  private static final long serialVersionUID = -1L;

  private final PostService postService;
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // req.getRequestDispatcher("/post/register.ftlh").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String title = req.getParameter("title");
    String body = req.getParameter("body");
    String limit = req.getParameter("limit");
    TopicForm form = new TopicForm(title, body, limit);
    
    resp.sendRedirect("/");
  }

  @Inject
  public PostServlet(PostService postService) {
    this.postService = postService;
  }
}
