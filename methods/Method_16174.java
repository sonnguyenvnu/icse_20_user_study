@Override public Object resolveArgument(MethodParameter parameter,ModelAndViewContainer mavContainer,NativeWebRequest webRequest,WebDataBinderFactory binderFactory) throws Exception {
  JsonParam jsonParam=parameter.getParameterAnnotation(JsonParam.class);
  String object=webRequest.getParameter(jsonParam.value());
  if (null != object) {
    Class type=jsonParam.type() != Void.class ? jsonParam.type() : parameter.getParameterType();
    return fastJsonHttpMessageConverter.readByString(type,object);
  }
  return null;
}
