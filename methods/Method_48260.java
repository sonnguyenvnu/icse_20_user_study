public void add(String store,String documentId,IndexEntry entry,boolean isNew){
  getIndexMutation(store,documentId,isNew,false).addition(new IndexEntry(entry.field,entry.value,entry.getMetaData()));
}
