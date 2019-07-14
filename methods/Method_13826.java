public static String getImportPreviewBatchSize(){
  if (extensionProperties == null) {
    return DEFAULT_PREVIEW_BATCH_SIZE;
  }
  return extensionProperties.getProperty("preview.batchSize",DEFAULT_PREVIEW_BATCH_SIZE);
}
