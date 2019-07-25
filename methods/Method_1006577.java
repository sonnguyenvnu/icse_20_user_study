private static <T>Predicate<T> negate(final Predicate<T> original){
  return new Predicate<T>(){
    public boolean test(    T t){
      return !original.test(t);
    }
  }
;
}
