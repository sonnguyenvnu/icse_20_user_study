private Class getJavaType(String type){
  if (StringUtils.isEmpty(type)) {
    return String.class;
  }
  Class clazz=classMapping.get(type);
  if (clazz == null) {
    try {
      clazz=Class.forName(type);
    }
 catch (    ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
  return clazz;
}
