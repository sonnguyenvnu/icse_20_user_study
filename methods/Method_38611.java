private String scopeName(final BeanDefinition beanDefinition){
  String scopeName=beanDefinition.scope().getSimpleName();
  scopeName=StringUtil.cutSuffix(scopeName,"Scope");
  return scopeName.toLowerCase();
}
