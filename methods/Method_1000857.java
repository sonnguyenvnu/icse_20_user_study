private static <T>List<T> _copy(List<T> src){
  return (src == null) ? null : new ArrayList<T>(src);
}
