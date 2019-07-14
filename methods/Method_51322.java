@Override public String errorFor(List<V> values){
  String err;
  for (  V value2 : values) {
    err=valueErrorFor(value2);
    if (err != null) {
      return err;
    }
  }
  return null;
}
