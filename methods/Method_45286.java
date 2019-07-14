private <T>Function<? super T,T> toInstance(final Class<T> clazz){
  return new Function<T,T>(){
    @Override public T apply(    final T input){
      return clazz.cast(input);
    }
  }
;
}
