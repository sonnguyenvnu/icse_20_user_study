@Override public void insert(MutableTreeNode newChild,int childIndex){
  super.insert(newChild,childIndex);
  if (myAdded && getTree() != null && !getTree().isDisposed()) {
    ((MPSTreeNode)getChildAt(childIndex)).addThisAndChildren();
  }
  updateErrorState();
}
