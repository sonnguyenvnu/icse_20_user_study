public FastJsonContainer beforeBodyWrite(Object body,MethodParameter returnType,MediaType selectedContentType,Class<? extends HttpMessageConverter<?>> selectedConverterType,ServerHttpRequest request,ServerHttpResponse response){
  FastJsonContainer container=getOrCreateContainer(body);
  beforeBodyWriteInternal(container,selectedContentType,returnType,request,response);
  return container;
}
