static protected void respond(HttpServletResponse response,String content) throws IOException, ServletException {
  response.setCharacterEncoding("UTF-8");
  response.setStatus(HttpServletResponse.SC_OK);
  Writer w=response.getWriter();
  if (w != null) {
    w.write(content);
    w.flush();
    w.close();
  }
 else {
    throw new ServletException("response returned a null writer");
  }
}
