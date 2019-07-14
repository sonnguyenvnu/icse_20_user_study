protected boolean genericTypeInstanceOf(Class type,AuthorizingContext context){
  Class entity=ClassUtils.getGenericType(context.getParamContext().getTarget().getClass());
  return null != entity && ClassUtils.instanceOf(entity,type);
}
