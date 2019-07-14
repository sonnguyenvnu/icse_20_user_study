static public void respondException(HttpServletResponse response,Exception e) throws IOException, ServletException {
  logger.warn("Exception caught",e);
  if (response == null) {
    throw new ServletException("Response object can't be null");
  }
  StringWriter sw=new StringWriter();
  PrintWriter pw=new PrintWriter(sw);
  e.printStackTrace(pw);
  pw.flush();
  sw.flush();
  response.setCharacterEncoding("UTF-8");
  response.setHeader("Content-Type","application/json");
  Writer w=response.getWriter();
  JsonGenerator writer=ParsingUtilities.mapper.getFactory().createGenerator(w);
  writer.writeStartObject();
  writer.writeStringField("code","error");
  writer.writeStringField("message",e.getMessage());
  writer.writeStringField("stack",sw.toString());
  writer.writeEndObject();
  writer.flush();
  writer.close();
  w.flush();
  w.close();
}
