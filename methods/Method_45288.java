private <T extends SimpleRestSetting>Predicate<T> isForMethod(final HttpMethod method){
  return new Predicate<T>(){
    @Override public boolean apply(    final T input){
      return input.isSimple() && input.isFor(method);
    }
  }
;
}
