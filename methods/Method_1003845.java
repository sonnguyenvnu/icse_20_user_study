/** 
 * A convenience shorthand for  {@link SessionData#set(Object,SessionSerializer)}.
 * @param value the value
 * @param serializer the serializer
 * @param < T > the type
 * @return the operation for setting the value
 */
default <T>Operation set(T value,SessionSerializer serializer){
  return getData().operation(d -> d.set(value,serializer));
}
