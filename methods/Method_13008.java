public void indexesChanged(Collection<Future<Indexes>> collectionOfFutureIndexes){
  if (openTypeView.isVisible()) {
    this.collectionOfFutureIndexes=collectionOfFutureIndexes;
    updateList(openTypeView.getPattern());
  }
}
