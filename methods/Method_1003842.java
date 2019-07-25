/** 
 * A convenience shorthand for  {@link SessionData#require(SessionKey,SessionSerializer)}.
 * @param key the object key
 * @param serializer the serializer
 * @param < T > the type
 * @return the value for the given key
 */
default <T>Promise<T> require(SessionKey<T> key,SessionSerializer serializer){
  return getData().map(d -> d.require(key,serializer));
}
