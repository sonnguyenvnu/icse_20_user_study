/** 
 * Invoked only if the converter type is  {@code FastJsonpHttpMessageConverter4}.
 */
public void beforeBodyWriteInternal(MappingFastJsonValue bodyContainer,MediaType contentType,MethodParameter returnType,ServerHttpRequest request,ServerHttpResponse response){
  HttpServletRequest servletRequest=((ServletServerHttpRequest)request).getServletRequest();
  for (  String name : this.jsonpQueryParamNames) {
    String value=servletRequest.getParameter(name);
    if (value != null) {
      if (!isValidJsonpQueryParam(value)) {
        continue;
      }
      bodyContainer.setJsonpFunction(value);
      break;
    }
  }
}
