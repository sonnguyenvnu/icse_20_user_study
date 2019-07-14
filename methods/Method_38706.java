/** 
 * Creates new JSON context.
 */
public JsonContext createJsonContext(final Appendable appendable){
  return new JsonContext(this,appendable);
}
