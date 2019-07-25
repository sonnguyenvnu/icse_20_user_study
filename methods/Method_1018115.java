@Override public void commit(@Nonnull final String indexName){
  buildTransportClient();
  client.admin().indices().prepareRefresh(indexName).execute().addListener(new ActionListener<RefreshResponse>(){
    @Override public void onResponse(    final RefreshResponse refreshResponse){
      log.debug("Committed index: {}",indexName);
    }
    @Override public void onFailure(    final Throwable e){
      log.warn("Failed to commit index: {}",indexName,e);
    }
  }
);
}
