/** 
 * Returns all includes for given type. Returns an empty array when no includes are defined.
 */
public TypeData lookupTypeData(final Class type){
  TypeData typeData=typeDataMap.get(type);
  if (typeData == null) {
    if (serializationSubclassAware) {
      typeData=findSubclassTypeData(type);
    }
    if (typeData == null) {
      typeData=scanClassForAnnotations(type);
      typeDataMap.put(type,typeData);
    }
  }
  return typeData;
}
