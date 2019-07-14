public static Class<?> getClass(String name){
  try {
    return ClassLoaderUtil.class.getClassLoader().loadClass(name);
  }
 catch (  ClassNotFoundException|NoClassDefFoundError e) {
    throw new RuntimeException(e);
  }
}
