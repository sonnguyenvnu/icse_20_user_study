private BackendException storageException(Exception solrException){
  return new TemporaryBackendException("Unable to complete query on Solr.",solrException);
}
