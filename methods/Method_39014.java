/** 
 * Registers new Madvoc component.
 */
protected void acceptMadvocComponentClass(final Class componentClass){
  if (componentClass == null) {
    return;
  }
  if (!checkClass(componentClass)) {
    return;
  }
  madvocComponents.add(() -> madvocContainer.registerComponent(componentClass));
}
