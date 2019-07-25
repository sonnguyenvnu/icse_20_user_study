public LazyNumber eval(final LazyNumber v1,final LazyNumber v2){
  return new LazyNumber(){
    public Number eval(){
      return AbstractOperator.this.eval(v1.eval(),v2.eval());
    }
    public String getString(){
      return String.valueOf(AbstractOperator.this.eval(v1.eval(),v2.eval()));
    }
  }
;
}
