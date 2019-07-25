@SuppressFBWarnings(value="DE_MIGHT_IGNORE",justification="Exception ignored while clearing previous maps before reinitialising.") @Override public void initialise(final String graphId,final Schema schema,final StoreProperties properties) throws StoreException {
  MapStore.resetStaticMap();
  try {
    super.initialise(graphId,schema,properties);
  }
 catch (  final Exception e) {
  }
  if (null != getMapImpl()) {
    getMapImpl().clear();
  }
  super.initialise(graphId,schema,properties);
}
