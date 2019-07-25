static Chain root(Object value){
  return new Chain(null,Preconditions.checkNotNull(value)){
    @Override public Class<?> getValueType(){
      return getValue().getClass();
    }
  }
;
}
