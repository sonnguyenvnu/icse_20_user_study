public static <T>List<T> union(List<T>... sets){
  List<T> result=new ArrayList<>();
  for (  List<T> s : sets) {
    result.addAll(s);
  }
  return result;
}
