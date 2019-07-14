protected void panelClosed(){
  SwingUtil.invokeLater(() -> {
    Collection<Future<Indexes>> collectionOfFutureIndexes=getCollectionOfFutureIndexes();
    for (    IndexesChangeListener listener : containerChangeListeners) {
      listener.indexesChanged(collectionOfFutureIndexes);
    }
    if (currentPage instanceof IndexesChangeListener) {
      ((IndexesChangeListener)currentPage).indexesChanged(collectionOfFutureIndexes);
    }
  }
);
}
