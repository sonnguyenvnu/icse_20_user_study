private <T>Predicate<Class<? extends T>> isInstance(final T target){
  return new Predicate<Class<? extends T>>(){
    @Override public boolean apply(    final Class<? extends T> input){
      return input.isInstance(target);
    }
  }
;
}
