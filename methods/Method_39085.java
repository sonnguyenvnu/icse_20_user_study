/** 
 * Builds injection point.
 */
protected InjectionPoint buildInjectionPoint(final String annotationValue,final String defaultValue,final String propertyName,final Class propertyType,final Class<? extends MadvocScope> scope){
  final String value=annotationValue.trim();
  final String name, targetName;
  if (StringUtil.isNotBlank(value)) {
    name=value;
    targetName=propertyName;
  }
 else {
    name=propertyName;
    targetName=null;
  }
  return new InjectionPoint(propertyType,name,targetName,scopeResolver.defaultOrScopeType(scope),defaultValue);
}
