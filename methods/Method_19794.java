@PostConstruct public void init(){
  List<HandlerMethodArgumentResolver> argumentResolvers=requestMappingHandlerAdapter.getArgumentResolvers();
  List<HandlerMethodArgumentResolver> newArgumentResolvers=new ArrayList<>(argumentResolvers.size() + 1);
  newArgumentResolvers.add(0,new PropertiesHandlerMethodArgumentResolver());
  newArgumentResolvers.addAll(argumentResolvers);
  requestMappingHandlerAdapter.setArgumentResolvers(newArgumentResolvers);
  List<HandlerMethodReturnValueHandler> returnValueHandlers=requestMappingHandlerAdapter.getReturnValueHandlers();
  List<HandlerMethodReturnValueHandler> newReturnValueHandlers=new ArrayList<>(returnValueHandlers.size() + 1);
  newReturnValueHandlers.add(0,new PropertiesHandlerMethodReturnValueHandler());
  newReturnValueHandlers.addAll(returnValueHandlers);
  requestMappingHandlerAdapter.setReturnValueHandlers(newReturnValueHandlers);
}
