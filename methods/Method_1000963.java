/** 
 * Factory method for constructing an instance.
 */
public static ReadOnlyClassToSerializerMap from(SerializerCache shared,SimpleLookupCache<TypeKey,JsonSerializer<Object>> src){
  return new ReadOnlyClassToSerializerMap(shared,src);
}
