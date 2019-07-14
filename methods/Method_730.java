public boolean supports(MethodParameter returnType,Class<? extends HttpMessageConverter<?>> converterType){
  return FastJsonHttpMessageConverter.class.isAssignableFrom(converterType) && (returnType.getContainingClass().isAnnotationPresent(ResponseJSONP.class) || returnType.hasMethodAnnotation(ResponseJSONP.class));
}
