public List<HystrixProperty> getThreadPoolProperties(){
  if (!isCommandAnnotationPresent())   return Collections.emptyList();
  return getOrDefault(new Supplier<List<HystrixProperty>>(){
    @Override public List<HystrixProperty> get(){
      return ImmutableList.copyOf(hystrixCommand.threadPoolProperties());
    }
  }
,new Supplier<List<HystrixProperty>>(){
    @Override public List<HystrixProperty> get(){
      return hasDefaultProperties() ? ImmutableList.copyOf(defaultProperties.threadPoolProperties()) : Collections.<HystrixProperty>emptyList();
    }
  }
,this.<HystrixProperty>nonEmptyList());
}
