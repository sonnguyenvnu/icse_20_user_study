public List<HystrixException> getRaiseHystrixExceptions(){
  return getOrDefault(new Supplier<List<HystrixException>>(){
    @Override public List<HystrixException> get(){
      return ImmutableList.copyOf(hystrixCommand.raiseHystrixExceptions());
    }
  }
,new Supplier<List<HystrixException>>(){
    @Override public List<HystrixException> get(){
      return hasDefaultProperties() ? ImmutableList.copyOf(defaultProperties.raiseHystrixExceptions()) : Collections.<HystrixException>emptyList();
    }
  }
,this.<HystrixException>nonEmptyList());
}
