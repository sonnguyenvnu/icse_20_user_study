/** 
 * Resolves property injection points.
 */
public PropertyInjectionPoint[] resolvePropertyInjectionPoint(final Class type,final boolean autowire){
  return propertyResolver.resolve(type,autowire);
}
