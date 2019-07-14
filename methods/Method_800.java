public static List<FieldInfo> computeGettersWithFieldBase(Class<?> clazz,Map<String,String> aliasMap,boolean sorted,PropertyNamingStrategy propertyNamingStrategy){
  Map<String,FieldInfo> fieldInfoMap=new LinkedHashMap<String,FieldInfo>();
  for (Class<?> currentClass=clazz; currentClass != null; currentClass=currentClass.getSuperclass()) {
    Field[] fields=currentClass.getDeclaredFields();
    computeFields(currentClass,aliasMap,propertyNamingStrategy,fieldInfoMap,fields);
  }
  return getFieldInfos(clazz,sorted,fieldInfoMap);
}
