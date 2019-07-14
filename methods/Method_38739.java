/** 
 * Get type serializer from map. First the current map is used. If element is missing, default map will be used, if exist.
 */
protected TypeJsonSerializer lookupSerializer(final Class type){
  TypeJsonSerializer tjs=map.get(type);
  if (tjs == null) {
    if (defaultSerializerMap != null) {
      tjs=defaultSerializerMap.map.get(type);
    }
  }
  return tjs;
}
