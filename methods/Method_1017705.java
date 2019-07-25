@Override public Object convert(String raw){
  try {
    return ctor.newInstance(raw);
  }
 catch (  Exception ex) {
    throw reflectionException(ex);
  }
}
