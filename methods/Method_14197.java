static protected void respond(HttpServletResponse response,String status,String message) throws IOException {
  Writer w=response.getWriter();
  JsonGenerator writer=ParsingUtilities.mapper.getFactory().createGenerator(w);
  writer.writeStartObject();
  writer.writeStringField("status",status);
  writer.writeStringField("message",message);
  writer.writeEndObject();
  writer.flush();
  writer.close();
  w.flush();
  w.close();
}
