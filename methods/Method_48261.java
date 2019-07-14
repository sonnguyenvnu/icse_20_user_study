private IndexMutation getIndexMutation(String store,String documentId,boolean isNew,boolean isDeleted){
  final Map<String,IndexMutation> storeMutations=mutations.computeIfAbsent(store,k -> new HashMap<>(DEFAULT_INNER_MAP_SIZE));
  IndexMutation m=storeMutations.get(documentId);
  if (m == null) {
    m=new IndexMutation(keyInformation.get(store),isNew,isDeleted);
    storeMutations.put(documentId,m);
  }
 else {
    if (isNew && m.isDeleted()) {
      m.resetDelete();
      assert !m.isNew() && !m.isDeleted();
    }
  }
  return m;
}
