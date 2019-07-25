/** 
 * A convenience shorthand for  {@link SessionData#require(Class,SessionSerializer)}.
 * @param type the type
 * @param serializer the serializer
 * @param < T > the type
 * @return the value for the given key
 */
default <T>Promise<T> require(Class<T> type,SessionSerializer serializer){
  return getData().map(d -> d.require(type,serializer));
}
