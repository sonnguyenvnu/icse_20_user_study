/** 
 * Parses input JSON to a list with specified component type.
 */
public <T>List<T> parseAsList(final String string,final Class<T> componentType){
  return new JsonParser().map(JsonParser.VALUES,componentType).parse(string);
}
