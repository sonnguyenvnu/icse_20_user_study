public Object beforeBodyWrite(Object body,MethodParameter returnType,MediaType selectedContentType,Class<? extends HttpMessageConverter<?>> selectedConverterType,ServerHttpRequest request,ServerHttpResponse response){
  MappingFastJsonValue container=getOrCreateContainer(body);
  beforeBodyWriteInternal(container,selectedContentType,returnType,request,response);
  return container;
}
