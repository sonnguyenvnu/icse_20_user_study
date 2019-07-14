@Override public HystrixCommand.Setter createSetter(FilterInvoker invoker,SofaRequest request){
  Method clientMethod=request.getMethod();
  if (!SETTER_CACHE.containsKey(clientMethod)) {
synchronized (DefaultSetterFactory.class) {
      if (!SETTER_CACHE.containsKey(clientMethod)) {
        String interfaceId=invoker.getConfig().getInterfaceId();
        String commandKey=generateCommandKey(interfaceId,request.getMethod());
        HystrixCommand.Setter setter=HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey(interfaceId)).andCommandKey(HystrixCommandKey.Factory.asKey(commandKey));
        SETTER_CACHE.put(clientMethod,setter);
      }
    }
  }
  return SETTER_CACHE.get(clientMethod);
}
