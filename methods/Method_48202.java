private void setupJanusGraphVersion(ModifiableConfiguration globalWrite){
  Preconditions.checkArgument(!globalWrite.has(INITIAL_JANUSGRAPH_VERSION),"Database has already been initialized but not frozen");
  globalWrite.set(INITIAL_JANUSGRAPH_VERSION,JanusGraphConstants.VERSION);
}
