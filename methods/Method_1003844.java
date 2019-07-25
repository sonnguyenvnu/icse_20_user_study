/** 
 * A convenience shorthand for  {@link SessionData#require(String,SessionSerializer)}.
 * @param name the object name
 * @param serializer the serializer
 * @return the value for the given key
 */
default Promise<?> require(String name,SessionSerializer serializer){
  return getData().map(d -> d.require(name,serializer));
}
