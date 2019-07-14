static protected void respondJSON(HttpServletResponse response,Object o,Properties options) throws IOException {
  response.setCharacterEncoding("UTF-8");
  response.setHeader("Content-Type","application/json");
  response.setHeader("Cache-Control","no-cache");
  Writer w=response.getWriter();
  ParsingUtilities.defaultWriter.writeValue(w,o);
  w.flush();
  w.close();
}
