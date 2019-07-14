/** 
 * Serializes the object using  {@link jodd.json.TypeJsonSerializer type serializer}. Returns <code>true</code> if object was written, otherwise returns <code>false</code>.
 */
public boolean serialize(final Object object){
  if (object == null) {
    write(NULL);
    return true;
  }
  TypeJsonSerializer typeJsonSerializer=null;
  if (serializerResolver != null) {
    typeJsonSerializer=serializerResolver.apply(object);
  }
  if (typeJsonSerializer == null) {
    if (jsonSerializer.pathSerializersMap != null) {
      typeJsonSerializer=jsonSerializer.pathSerializersMap.get(path);
    }
    final Class type=object.getClass();
    if (jsonSerializer.typeSerializersMap != null) {
      typeJsonSerializer=jsonSerializer.typeSerializersMap.lookup(type);
    }
    if (typeJsonSerializer == null) {
      typeJsonSerializer=TypeJsonSerializerMap.get().lookup(type);
    }
  }
  return typeJsonSerializer.serialize(this,object);
}
