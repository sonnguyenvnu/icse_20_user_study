private <T>Predicate<List<T>> nonEmptyList(){
  return new Predicate<List<T>>(){
    @Override public boolean apply(    @Nullable List<T> input){
      return input != null && !input.isEmpty();
    }
  }
;
}
