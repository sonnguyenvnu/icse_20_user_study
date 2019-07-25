public static <T>SingleTransformer<List<T>,List<T>> filter(Predicate<T> predicate){
  return upstream -> upstream.map(list -> {
    List<T> result=new ArrayList<>();
    for (    T item : list) {
      if (predicate.test(item)) {
        result.add(item);
      }
    }
    return result;
  }
);
}
