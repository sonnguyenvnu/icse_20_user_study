/** 
 * Subclasses shall invoke once model had changed
 */
protected void removed(SNode n){
  if (myNodeMap.isEmpty()) {
    return;
  }
  ConceptInstanceMap toDelete=build(new ConceptNodeMapBuilder(n));
synchronized (myNodeMap) {
    myNodeMap.forget(toDelete);
  }
}
