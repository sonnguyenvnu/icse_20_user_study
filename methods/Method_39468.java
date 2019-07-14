/** 
 * Creates a proxy from given  {@link Aspect}.
 */
@SuppressWarnings("unchecked") public static <T>T proxyOf(final Aspect aspect){
  final Object target=aspect.getTarget();
  return (T)newProxyInstance(target.getClass().getClassLoader(),aspect,target.getClass().getInterfaces());
}
