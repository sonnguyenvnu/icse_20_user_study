@Nullable private <T>List<T> convertList(@Nullable Object o){
  if (o == null) {
    return null;
  }
 else {
    List<Map<String,Object>> in=(List<Map<String,Object>>)o;
    List<T> out=new ArrayList<>();
    for (    Object x : (List)in) {
      if (!(x instanceof Map)) {
        _.die("not a map: " + x);
      }
    }
    for (    Map<String,Object> m : in) {
      Node n=convert(m);
      if (n != null) {
        out.add((T)n);
      }
    }
    return out;
  }
}
