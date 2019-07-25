void retry(final Supplier<Void> function,final String label){
  for (int i=0; ; ++i) {
    try {
      function.get();
      break;
    }
 catch (    final Exception e) {
      if (i >= CREATE_ELASTIC_ADMIN_RETRY_ATTEMPTS) {
        logger.error("Failed to {} after {} attempts",label,CREATE_ELASTIC_ADMIN_RETRY_ATTEMPTS);
        throw new NoPersistedMetaDataException("Failed to " + label + " after " + CREATE_ELASTIC_ADMIN_RETRY_ATTEMPTS + " attempts",e);
      }
 else       logger.info("Retrying: {}",label);
    }
  }
}
