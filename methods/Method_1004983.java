public void initialise(final String graphId,final Schema schema,final StoreProperties properties) throws StoreException {
  LOGGER.debug("Initialising {}",getClass().getSimpleName());
  if (null == graphId) {
    throw new IllegalArgumentException("graphId is required");
  }
  this.graphId=graphId;
  this.schema=schema;
  setProperties(properties);
  updateJsonSerialiser();
  startCacheServiceLoader(properties);
  this.jobTracker=createJobTracker();
  optimiseSchema();
  validateSchemas();
  addOpHandlers();
  addExecutorService(properties);
}
