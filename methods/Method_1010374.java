/** 
 * Deletes all nodes in subtree starting with current. Differs from  {@link SNode#removeChild(org.jetbrains.mps.openapi.model.SNode)}.
 */
@Override public void delete(){
  assertCanChange();
  SNode p=getParent();
  if (p != null) {
    p.removeChild(this);
  }
 else   if (myOwner.getModel() != null) {
    myOwner.getModel().removeRootNode(this);
  }
}
