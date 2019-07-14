private void setupStorageVersion(ModifiableConfiguration globalWrite){
  Preconditions.checkArgument(!globalWrite.has(INITIAL_STORAGE_VERSION),"Database has already been initialized but not frozen");
  globalWrite.set(INITIAL_STORAGE_VERSION,JanusGraphConstants.STORAGE_VERSION);
}
