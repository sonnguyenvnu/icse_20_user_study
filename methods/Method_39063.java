/** 
 * Returns registered component or  {@code null} if component does not exist.
 */
public Object lookupComponent(final String componentName){
  return madpc.getBean(componentName);
}
