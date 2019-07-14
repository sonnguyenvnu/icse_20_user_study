@Override public void restore(Map<String,Map<String,List<IndexEntry>>> documents,KeyInformation.IndexRetriever information,BaseTransaction tx) throws BackendException {
  final List<ElasticSearchMutation> requests=new ArrayList<>();
  try {
    for (    final Map.Entry<String,Map<String,List<IndexEntry>>> stores : documents.entrySet()) {
      final List<ElasticSearchMutation> requestByStore=new ArrayList<>();
      final String store=stores.getKey();
      final String indexStoreName=getIndexStoreName(store);
      for (      final Map.Entry<String,List<IndexEntry>> entry : stores.getValue().entrySet()) {
        final String docID=entry.getKey();
        final List<IndexEntry> content=entry.getValue();
        if (content == null || content.size() == 0) {
          if (log.isTraceEnabled())           log.trace("Deleting entire document {}",docID);
          requestByStore.add(ElasticSearchMutation.createDeleteRequest(indexStoreName,store,docID));
        }
 else {
          if (log.isTraceEnabled())           log.trace("Adding entire document {}",docID);
          final Map<String,Object> source=getNewDocument(content,information.get(store));
          requestByStore.add(ElasticSearchMutation.createIndexRequest(indexStoreName,store,docID,source));
        }
      }
      if (!requestByStore.isEmpty() && ingestPipelines.containsKey(store)) {
        client.bulkRequest(requestByStore,String.valueOf(ingestPipelines.get(store)));
      }
 else       if (!requestByStore.isEmpty()) {
        requests.addAll(requestByStore);
      }
    }
    if (!requests.isEmpty())     client.bulkRequest(requests,null);
  }
 catch (  final Exception e) {
    throw convert(e);
  }
}
