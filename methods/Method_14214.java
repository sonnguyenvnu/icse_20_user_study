static public void respondJSON(HttpServletResponse response,Object o,Properties options) throws IOException {
  response.setCharacterEncoding("UTF-8");
  response.setHeader("Content-Type","application/json");
  Writer w=response.getWriter();
  ParsingUtilities.defaultWriter.writeValue(w,o);
  w.flush();
  w.close();
}
