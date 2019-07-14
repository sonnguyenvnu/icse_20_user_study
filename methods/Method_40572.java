public static Collection<String> toStringCollection(Collection<Integer> collection){
  List<String> ret=new ArrayList<String>();
  for (  Integer x : collection) {
    ret.add(x.toString());
  }
  return ret;
}
