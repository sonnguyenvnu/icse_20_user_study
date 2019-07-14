static public ArrayNode convertErrorsToJsonArray(List<Exception> exceptions){
  ArrayNode a=ParsingUtilities.mapper.createArrayNode();
  for (  Exception e : exceptions) {
    StringWriter sw=new StringWriter();
    e.printStackTrace(new PrintWriter(sw));
    ObjectNode o=ParsingUtilities.mapper.createObjectNode();
    JSONUtilities.safePut(o,"message",e.getLocalizedMessage());
    JSONUtilities.safePut(o,"stack",sw.toString());
    JSONUtilities.append(a,o);
  }
  return a;
}
