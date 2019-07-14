/** 
 * Lookups for the  {@link jodd.json.TypeJsonSerializer serializer} for given type.If serializer not found, then all interfaces and subclasses of the type are checked. Finally, if no serializer is found, object's serializer is returned.
 */
public TypeJsonSerializer lookup(final Class type){
  return cache.get(type,() -> _lookup(type));
}
