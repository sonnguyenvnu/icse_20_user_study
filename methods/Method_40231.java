@NotNull public static Collection<String> toStringCollection(@NotNull Collection<Integer> collection){
  List<String> ret=new ArrayList<>();
  for (  Integer x : collection) {
    ret.add(x.toString());
  }
  return ret;
}
