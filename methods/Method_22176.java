private ServletContextHandler buildServletContextHandler(){
  ServletContextHandler result=new ServletContextHandler(ServletContextHandler.SESSIONS);
  result.setContextPath("/");
  return result;
}
