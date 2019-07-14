private void finish(HttpServletResponse response) throws IOException {
  response.setCharacterEncoding("UTF-8");
  response.setHeader("Content-Type","text/html");
  PrintWriter writer=response.getWriter();
  writer.write("<html>" + "<body></body>" + "<script type='text/javascript'>" + "if (top.opener && top.opener.onauthorization) {" + "   top.opener.onauthorization(window);" + "}" + "self.close();" + "</script>" + "</html>");
  writer.flush();
}
