private boolean isLayoutRoot(InternalNode node){
  return mLayoutRoot.isNestedTreeHolder() ? node == mLayoutRoot.getNestedTree() : node == mLayoutRoot;
}
