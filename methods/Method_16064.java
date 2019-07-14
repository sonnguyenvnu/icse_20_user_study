@SuppressWarnings("all") public static <T>ToStringOperator<T> getOperator(Class<T> type){
  return cache.computeIfAbsent(type,DefaultToStringOperator::new);
}
