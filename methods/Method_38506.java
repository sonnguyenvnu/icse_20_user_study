/** 
 * Adds many query parameters at once. Although it accepts objects, each value will be converted to string.
 */
public HttpRequest query(final String name1,final Object value1,final Object... parameters){
  query(name1,value1 == null ? null : value1.toString());
  for (int i=0; i < parameters.length; i+=2) {
    String name=parameters[i].toString();
    String value=parameters[i + 1].toString();
    query.add(name,value);
  }
  return this;
}
