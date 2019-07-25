/** 
 * Creates a new  {@link Service} that decorates this {@link Service} with a new {@link Service} instanceof the specified  {@code serviceType}. The specified  {@link Class} must have a single-parameterconstructor which accepts this  {@link Service}.
 */
default <R extends Service<?,?>>R decorate(Class<R> serviceType){
  requireNonNull(serviceType,"serviceType");
  Constructor<?> constructor=null;
  for (  Constructor<?> c : serviceType.getConstructors()) {
    if (c.getParameterCount() != 1) {
      continue;
    }
    if (c.getParameterTypes()[0].isAssignableFrom(getClass())) {
      constructor=c;
      break;
    }
  }
  if (constructor == null) {
    throw new IllegalArgumentException("cannot find a matching constructor: " + serviceType.getName());
  }
  try {
    return (R)constructor.newInstance(this);
  }
 catch (  Exception e) {
    throw new IllegalStateException("failed to instantiate: " + serviceType.getName(),e);
  }
}
