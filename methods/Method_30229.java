public boolean isWriting(long itemCollectionId){
  return findWriter(itemCollectionId) != null;
}
