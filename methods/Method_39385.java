/** 
 * Resolves set injection points.
 */
public SetInjectionPoint[] resolveSetInjectionPoint(final Class type,final boolean autowire){
  return setResolver.resolve(type,autowire);
}
