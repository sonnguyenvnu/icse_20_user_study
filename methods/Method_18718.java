@VisibleForTesting static Predicate<String> startsWith(String prefix){
  return name -> name.startsWith(prefix);
}
