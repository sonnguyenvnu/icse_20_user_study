/** 
 * Change the root component measuring it on a background thread before updating the UI. If this  {@link LithoView} doesn't have a ComponentTree initialized, the root will becomputed synchronously.
 */
public void setComponentAsync(Component component){
  if (mComponentTree == null) {
    setComponentTree(ComponentTree.create(getComponentContext(),component).build());
  }
 else {
    mComponentTree.setRootAsync(component);
  }
}
