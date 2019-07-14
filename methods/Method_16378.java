@Override public AuthorizeDefinition parse(Class target,Method method,MethodInterceptorContext context){
  if (!ClassUtils.getUserClass(target).equals(DynamicFormOperationController.class) || context == null) {
    return null;
  }
  Authorize methodAuth=AopUtils.findMethodAnnotation(target,method,Authorize.class);
  return context.<String>getParameter("formId").map(formId -> getDefinition(formId,methodAuth == null ? new String[0] : methodAuth.action(),context)).orElse(null);
}
