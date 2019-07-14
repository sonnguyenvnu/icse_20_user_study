static <T>T makeMatcher(Class<T> klass){
  try {
    return klass.newInstance();
  }
 catch (  ReflectiveOperationException e) {
    throw new RuntimeException(e);
  }
}
