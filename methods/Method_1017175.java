public AsyncFuture<Void> configure(){
  final IndicesAdminClient indices=client.getClient().admin().indices();
  log.info("[{}] updating template for {}",templateName,index.template());
  final PutIndexTemplateRequestBuilder put=indices.preparePutTemplate(templateName);
  put.setSettings(type.getSettings());
  put.setTemplate(index.template());
  for (  final Map.Entry<String,Map<String,Object>> mapping : type.getMappings().entrySet()) {
    put.addMapping(mapping.getKey(),mapping.getValue());
  }
  final ResolvableFuture<Void> future=async.future();
  final ListenableActionFuture<PutIndexTemplateResponse> target=put.execute();
  target.addListener(new ActionListener<PutIndexTemplateResponse>(){
    @Override public void onResponse(    final PutIndexTemplateResponse response){
      if (!response.isAcknowledged()) {
        future.fail(new Exception("request not acknowledged"));
        return;
      }
      future.resolve(null);
    }
    @Override public void onFailure(    Exception e){
      future.fail(e);
    }
  }
);
  future.onCancelled(() -> target.cancel(false));
  return future;
}
