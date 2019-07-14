@SuppressWarnings("unchecked") private <T>T getOrDefault(Supplier<T> source,Supplier<T> defaultChoice,Predicate<T> isDefined){
  return getOrDefault(source,defaultChoice,isDefined,(Function<T,T>)identityFun);
}
