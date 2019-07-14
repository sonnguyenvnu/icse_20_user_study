@Override public void mutate(Map<String,Map<String,IndexMutation>> mutations,KeyInformation.IndexRetriever information,BaseTransaction tx) throws BackendException {
  final List<ElasticSearchMutation> requests=new ArrayList<>();
  try {
    for (    final Map.Entry<String,Map<String,IndexMutation>> stores : mutations.entrySet()) {
      final List<ElasticSearchMutation> requestByStore=new ArrayList<>();
      final String storeName=stores.getKey();
      final String indexStoreName=getIndexStoreName(storeName);
      for (      final Map.Entry<String,IndexMutation> entry : stores.getValue().entrySet()) {
        final String documentId=entry.getKey();
        final IndexMutation mutation=entry.getValue();
        assert mutation.isConsolidated();
        Preconditions.checkArgument(!(mutation.isNew() && mutation.isDeleted()));
        Preconditions.checkArgument(!mutation.isNew() || !mutation.hasDeletions());
        Preconditions.checkArgument(!mutation.isDeleted() || !mutation.hasAdditions());
        if (mutation.hasDeletions()) {
          if (mutation.isDeleted()) {
            log.trace("Deleting entire document {}",documentId);
            requestByStore.add(ElasticSearchMutation.createDeleteRequest(indexStoreName,storeName,documentId));
          }
 else {
            final String script=getDeletionScript(information,storeName,mutation);
            final Map<String,Object> doc=compat.prepareScript(script).build();
            requestByStore.add(ElasticSearchMutation.createUpdateRequest(indexStoreName,storeName,documentId,doc));
            log.trace("Adding script {}",script);
          }
        }
        if (mutation.hasAdditions()) {
          if (mutation.isNew()) {
            log.trace("Adding entire document {}",documentId);
            final Map<String,Object> source=getNewDocument(mutation.getAdditions(),information.get(storeName));
            requestByStore.add(ElasticSearchMutation.createIndexRequest(indexStoreName,storeName,documentId,source));
          }
 else {
            final Map upsert;
            if (!mutation.hasDeletions()) {
              upsert=getNewDocument(mutation.getAdditions(),information.get(storeName));
            }
 else {
              upsert=null;
            }
            final String inline=getAdditionScript(information,storeName,mutation);
            if (!inline.isEmpty()) {
              final ImmutableMap.Builder builder=compat.prepareScript(inline);
              requestByStore.add(ElasticSearchMutation.createUpdateRequest(indexStoreName,storeName,documentId,builder,upsert));
              log.trace("Adding script {}",inline);
            }
            final Map<String,Object> doc=getAdditionDoc(information,storeName,mutation);
            if (!doc.isEmpty()) {
              final ImmutableMap.Builder builder=ImmutableMap.builder().put(ES_DOC_KEY,doc);
              requestByStore.add(ElasticSearchMutation.createUpdateRequest(indexStoreName,storeName,documentId,builder,upsert));
              log.trace("Adding update {}",doc);
            }
          }
        }
      }
      if (!requestByStore.isEmpty() && ingestPipelines.containsKey(storeName)) {
        client.bulkRequest(requestByStore,String.valueOf(ingestPipelines.get(storeName)));
      }
 else       if (!requestByStore.isEmpty()) {
        requests.addAll(requestByStore);
      }
    }
    if (!requests.isEmpty()) {
      client.bulkRequest(requests,null);
    }
  }
 catch (  final Exception e) {
    log.error("Failed to execute bulk Elasticsearch mutation",e);
    throw convert(e);
  }
}
