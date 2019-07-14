/** 
 * Creates a proxy of given target and the aspect.
 */
@SuppressWarnings("unchecked") public static <T>T proxyOf(final T target,final Class<? extends Aspect> aspectClass){
  final Aspect aspect;
  try {
    aspect=ClassUtil.newInstance(aspectClass,target);
  }
 catch (  Exception e) {
    throw new IllegalArgumentException("Can't create new instance of aspect class",e);
  }
  return (T)newProxyInstance(target.getClass().getClassLoader(),aspect,target.getClass().getInterfaces());
}
