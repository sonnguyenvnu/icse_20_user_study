public void indexesChanged(Collection<Future<Indexes>> collectionOfFutureIndexes){
  if (openTypeHierarchyView.isVisible()) {
    this.collectionOfFutureIndexes=collectionOfFutureIndexes;
    openTypeHierarchyView.updateTree(collectionOfFutureIndexes);
  }
}
