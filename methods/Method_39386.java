/** 
 * Resolves destroy method points.
 */
public DestroyMethodPoint[] resolveDestroyMethodPoint(final Class type){
  return destroyMethodResolver.resolve(type);
}
