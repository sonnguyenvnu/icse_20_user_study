private static List<FieldInfo> getFieldInfos(Class<?> clazz,boolean sorted,Map<String,FieldInfo> fieldInfoMap){
  List<FieldInfo> fieldInfoList=new ArrayList<FieldInfo>();
  String[] orders=null;
  JSONType annotation=TypeUtils.getAnnotation(clazz,JSONType.class);
  if (annotation != null) {
    orders=annotation.orders();
  }
  if (orders != null && orders.length > 0) {
    LinkedHashMap<String,FieldInfo> map=new LinkedHashMap<String,FieldInfo>(fieldInfoList.size());
    for (    FieldInfo field : fieldInfoMap.values()) {
      map.put(field.name,field);
    }
    int i=0;
    for (    String item : orders) {
      FieldInfo field=map.get(item);
      if (field != null) {
        fieldInfoList.add(field);
        map.remove(item);
      }
    }
    for (    FieldInfo field : map.values()) {
      fieldInfoList.add(field);
    }
  }
 else {
    for (    FieldInfo fieldInfo : fieldInfoMap.values()) {
      fieldInfoList.add(fieldInfo);
    }
    if (sorted) {
      Collections.sort(fieldInfoList);
    }
  }
  return fieldInfoList;
}
