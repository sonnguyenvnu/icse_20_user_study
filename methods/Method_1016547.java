Class<?> clazz(String className){
  try {
    return this.classLoader.loadClass(className);
  }
 catch (  ClassNotFoundException e) {
    throw new ReflectiveException(e);
  }
}
