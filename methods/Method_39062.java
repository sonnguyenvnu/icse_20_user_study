/** 
 * Returns existing component. Throws an exception if component is not registered.
 */
public <T>T requestComponent(final Class<T> component){
  T existingComponent=lookupComponent(component);
  if (existingComponent == null) {
    throw new MadvocException("Madvoc component not found: " + component.getName());
  }
  return existingComponent;
}
