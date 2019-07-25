@Override public Object convert(String raw){
  try {
    return method.invoke(null,raw);
  }
 catch (  Exception ex) {
    throw reflectionException(ex);
  }
}
