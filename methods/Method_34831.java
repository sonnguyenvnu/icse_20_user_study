@Override public HystrixDynamicProperty<Integer> getInteger(final String name,final Integer fallback){
  return new HystrixDynamicProperty<Integer>(){
    @Override public String getName(){
      return name;
    }
    @Override public Integer get(){
      return Integer.getInteger(name,fallback);
    }
    @Override public void addCallback(    Runnable callback){
    }
  }
;
}
