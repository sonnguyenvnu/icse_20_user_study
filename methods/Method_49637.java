private void handleRemovalsFromIndex(String collectionName,String keyIdField,String docId,List<IndexEntry> fieldDeletions,KeyInformation.IndexRetriever information) throws SolrServerException, IOException, BackendException {
  final Map<String,String> fieldDeletes=new HashMap<>(1);
  fieldDeletes.put("set",null);
  final SolrInputDocument doc=new SolrInputDocument();
  doc.addField(keyIdField,docId);
  for (  final IndexEntry v : fieldDeletions) {
    final KeyInformation keyInformation=information.get(collectionName,v.field);
    final Map<String,Object> deletes=collectFieldValues(fieldDeletions,collectionName,information);
    deletes.keySet().forEach(vertex -> {
      final Map<String,Object> remove;
      if (keyInformation.getCardinality() == Cardinality.SINGLE) {
        remove=(Map)fieldDeletes;
      }
 else {
        remove=new HashMap<>(1);
        remove.put("remove",deletes.get(vertex));
      }
      doc.setField(vertex,remove);
    }
);
  }
  final UpdateRequest singleDocument=newUpdateRequest();
  singleDocument.add(doc);
  solrClient.request(singleDocument,collectionName);
}
