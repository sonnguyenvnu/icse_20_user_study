/** 
 * Lookups type data and creates one if missing.
 */
protected TypeData _lookupTypeData(final Class type){
  TypeData typeData=typeDataMap.get(type);
  if (typeData == null) {
    typeData=scanClassForAnnotations(type);
    typeDataMap.put(type,typeData);
  }
  return typeData;
}
