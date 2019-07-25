private static Function<String,Predicate<String>> and(Function<String,Predicate<String>> first,Function<String,Predicate<String>> second){
  if (first == MapperPlugin.NOOP_FIELD_FILTER) {
    return second;
  }
  if (second == MapperPlugin.NOOP_FIELD_FILTER) {
    return first;
  }
  return index -> {
    Predicate<String> firstPredicate=first.apply(index);
    Predicate<String> secondPredicate=second.apply(index);
    if (firstPredicate == MapperPlugin.NOOP_FIELD_PREDICATE) {
      return secondPredicate;
    }
    if (secondPredicate == MapperPlugin.NOOP_FIELD_PREDICATE) {
      return firstPredicate;
    }
    return firstPredicate.and(secondPredicate);
  }
;
}
