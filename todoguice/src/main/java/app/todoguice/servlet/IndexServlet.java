package app.todoguice.servlet;

import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import app.todoguice.service.IndexService;

@Singleton
public class IndexServlet extends HttpServlet {

  private static final long serialVersionUID = -1L;

  private final IndexService indexService;
  
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    req.setAttribute("title", this.indexService.getTitle());
    req.getRequestDispatcher("/index.ftlh").forward(req, resp);
  }

  @Inject
  public IndexServlet(IndexService indexService) {
    this.indexService = indexService;
  }
}
