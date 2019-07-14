/** 
 * Creates a lazy implementation of the JSON parser.
 */
public static JsonParser createLazyOne(){
  return new JsonParser().lazy(true);
}
