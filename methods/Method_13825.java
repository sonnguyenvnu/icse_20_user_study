public static String getImportCreateBatchSize(){
  if (extensionProperties == null) {
    return DEFAULT_CREATE_PROJ_BATCH_SIZE;
  }
  return extensionProperties.getProperty("create.batchSize",DEFAULT_CREATE_PROJ_BATCH_SIZE);
}
