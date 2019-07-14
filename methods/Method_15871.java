public static String getJavaType(Class type){
  String javaType=simpleName.get(type);
  if (javaType == null) {
    javaType=type.getName();
  }
  return javaType;
}
