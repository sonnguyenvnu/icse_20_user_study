private VoteItemCollectionWriter findWriter(long itemCollectionId){
  for (  VoteItemCollectionWriter writer : getWriters()) {
    if (writer.getItemCollectionId() == itemCollectionId) {
      return writer;
    }
  }
  return null;
}
