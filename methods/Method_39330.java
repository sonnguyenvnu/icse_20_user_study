/** 
 * Injects all parameters.
 */
public void injectParams(final ParamManager paramManager,final boolean implicitParamInjection){
  if (beanDefinition.name == null) {
    return;
  }
  if (implicitParamInjection) {
    final int len=beanDefinition.name.length() + 1;
    for (    final String param : beanDefinition.params) {
      final Object value=paramManager.get(param);
      final String destination=param.substring(len);
      try {
        BeanUtil.declared.setProperty(bean,destination,value);
      }
 catch (      Exception ex) {
        throw new PetiteException("Unable to set parameter: '" + param + "' to bean: " + beanDefinition.name,ex);
      }
    }
  }
  for (  final ValueInjectionPoint pip : beanDefinition.values) {
    final String value=paramManager.parseKeyTemplate(pip.valueTemplate);
    try {
      BeanUtil.declared.setProperty(bean,pip.property,value);
    }
 catch (    Exception ex) {
      throw new PetiteException("Unable to set value for: '" + pip.valueTemplate + "' to bean: " + beanDefinition.name,ex);
    }
  }
}
