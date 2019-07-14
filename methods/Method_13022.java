public void indexesChanged(Collection<Future<Indexes>> collectionOfFutureIndexes){
  if (searchInConstantPoolsView.isVisible()) {
    this.collectionOfFutureIndexes=collectionOfFutureIndexes;
    updateTree(searchInConstantPoolsView.getPattern(),searchInConstantPoolsView.getFlags());
  }
}
