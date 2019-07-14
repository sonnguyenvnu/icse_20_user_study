/** 
 * Helper introduced to ease returning error messages from a response. Curiously this is not part of  {@link Command}: the respond method uses the "status" JSON key instead of the "code" one required by the JS code.
 * @param response
 * @param errorMessage
 * @throws IOException 
 */
public static void respondError(HttpServletResponse response,String errorMessage) throws IOException {
  Writer w=response.getWriter();
  JsonGenerator writer=ParsingUtilities.mapper.getFactory().createGenerator(w);
  writer.writeStartObject();
  writer.writeStringField("code","error");
  writer.writeStringField("message",errorMessage);
  writer.writeEndObject();
  writer.flush();
  writer.close();
  w.flush();
  w.close();
}
