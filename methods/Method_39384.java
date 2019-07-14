/** 
 * Resolves method injection points.
 */
public MethodInjectionPoint[] resolveMethodInjectionPoint(final Class type){
  return methodResolver.resolve(type);
}
