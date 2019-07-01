public void _XXXXX_() throws IOException, EagleServiceClientException {
  if (this.entityBucket.size() == 0 && LOG.isDebugEnabled()) {
    LOG.debug("No entities to flush");
    return;
  }
  LOG.info("Writing " + this.entityBucket.size() + " entities");
  GenericServiceAPIResponseEntity<String> response=this.client.create(this.entityBucket);
  if (!response.isSuccess()) {
    LOG.error("Got service exception: " + response.getException());
    throw new IOException("Service exception" + response.getException());
  }
 else {
    this.entityBucket.clear();
  }
}