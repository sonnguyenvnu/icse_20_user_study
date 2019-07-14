@Override public HystrixDynamicProperty<Long> getLong(final String name,final Long fallback){
  return new HystrixDynamicProperty<Long>(){
    @Override public String getName(){
      return name;
    }
    @Override public Long get(){
      return Long.getLong(name,fallback);
    }
    @Override public void addCallback(    Runnable callback){
    }
  }
;
}
