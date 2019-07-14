static Class<?> getClass(ClassName name){
  try {
    return Class.forName(name.reflectionName());
  }
 catch (  ClassNotFoundException|NoClassDefFoundError e) {
    return null;
  }
}
