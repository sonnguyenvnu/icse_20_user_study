public static <T>Collector<T,?,T> singletonCollector(){
  return Collectors.collectingAndThen(Collectors.toList(),list -> {
    if (list.size() > 1) {
      throw new IllegalStateException("List contains more than one element: " + list);
    }
    return list.size() > 0 ? list.get(0) : null;
  }
);
}
