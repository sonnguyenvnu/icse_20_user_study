private void checkJanusGraphStorageVersionEquality(ModifiableConfiguration globalWrite,String graphName){
  if (!Objects.equals(globalWrite.get(INITIAL_STORAGE_VERSION),JanusGraphConstants.STORAGE_VERSION)) {
    String storageVersion=(globalWrite.has(INITIAL_STORAGE_VERSION)) ? globalWrite.get(INITIAL_STORAGE_VERSION) : "1";
    throw new JanusGraphException(String.format(INCOMPATIBLE_STORAGE_VERSION_EXCEPTION,storageVersion,JanusGraphConstants.STORAGE_VERSION,graphName));
  }
}
