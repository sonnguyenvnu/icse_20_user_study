static <T extends EnumDict>List<T> getByMask(List<T> allOptions,long mask){
  if (allOptions.size() >= 64) {
    throw new UnsupportedOperationException("???????64?????!");
  }
  List<T> arr=new ArrayList<>();
  List<T> all=allOptions;
  for (  T t : all) {
    if (t.in(mask)) {
      arr.add(t);
    }
  }
  return arr;
}
