private void copy(Page source,Page parent,int index){
  Page target=source.copy(this);
  if (parent == null) {
    setInitialRoot(target,INITIAL_VERSION);
  }
 else {
    parent.setChild(index,target);
  }
  if (!source.isLeaf()) {
    for (int i=0; i < getChildPageCount(target); i++) {
      if (source.getChildPagePos(i) != 0) {
        copy(source.getChildPage(i),target,i);
      }
    }
    target.setComplete();
  }
  store.registerUnsavedMemory(target.getMemory());
  if (store.isSaveNeeded()) {
    store.commit();
  }
}
