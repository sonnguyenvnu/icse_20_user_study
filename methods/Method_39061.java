/** 
 * Returns registered component or  {@code null} if component is not registered.
 */
@SuppressWarnings({"unchecked"}) public <T>T lookupComponent(final Class<T> component){
  String name=resolveBaseComponentName(component);
  return (T)madpc.getBean(name);
}
