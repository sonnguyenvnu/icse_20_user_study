public static <T>Property<T> from(final Supplier<T> value){
  return new Property<T>(){
    @Override public T get(){
      return value.get();
    }
  }
;
}
