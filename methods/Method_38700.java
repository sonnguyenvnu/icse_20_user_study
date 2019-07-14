/** 
 * Defines custom  {@link jodd.json.TypeJsonSerializer} for given path.
 */
public JsonSerializer withSerializer(final String pathString,final TypeJsonSerializer typeJsonSerializer){
  if (pathSerializersMap == null) {
    pathSerializersMap=new HashMap<>();
  }
  pathSerializersMap.put(Path.parse(pathString),typeJsonSerializer);
  return this;
}
