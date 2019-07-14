protected void checkIndexesChange(JComponent page){
  if (page instanceof IndexesChangeListener) {
    Collection<Future<Indexes>> collectionOfFutureIndexes=getCollectionOfFutureIndexes();
    Integer currentHashcode=Integer.valueOf(collectionOfFutureIndexes.hashCode());
    Integer lastHashcode=(Integer)page.getClientProperty("collectionOfFutureIndexes-hashCode");
    if (!currentHashcode.equals(lastHashcode)) {
      ((IndexesChangeListener)page).indexesChanged(collectionOfFutureIndexes);
      page.putClientProperty("collectionOfFutureIndexes-hashCode",currentHashcode);
    }
  }
}
