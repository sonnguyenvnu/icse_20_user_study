@Override public HystrixDynamicProperty<String> getString(final String name,final String fallback){
  return new HystrixDynamicProperty<String>(){
    @Override public String getName(){
      return name;
    }
    @Override public String get(){
      return System.getProperty(name,fallback);
    }
    @Override public void addCallback(    Runnable callback){
    }
  }
;
}
