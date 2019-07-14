@Override public String formatMap(Map<String,?> map){
  try {
    return mapper.writeValueAsString(map);
  }
 catch (  Exception e) {
    throw new IllegalArgumentException("Cannot format json",e);
  }
}
