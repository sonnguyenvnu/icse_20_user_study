/** 
 * Defines callback for value serialization. It defines the instance of  {@link TypeJsonSerializer}to be used with the value. If  {@code null} is returned, default serializer will be resolved.
 */
public JsonSerializer onValue(final Function<Object,TypeJsonSerializer> function){
  this.serializerResolver=function;
  return this;
}
