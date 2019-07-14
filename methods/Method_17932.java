/** 
 * Whether the component needs updating. <p> For layout components, the framework will verify that none of the children of the component need updating, and that both components have the same number of children. Therefore this method just needs to determine any changes to the top-level component that would cause it to need to be updated (for example, a click handler was added). <p> For mount specs, the framework does nothing extra and this method alone determines whether the component is updated or not.
 * @param previous the previous component to compare against.
 * @param next the component that is now in use.
 * @return true if the component needs an update, false otherwise.
 */
protected boolean shouldUpdate(Component previous,Component next){
  return !previous.isEquivalentTo(next);
}
