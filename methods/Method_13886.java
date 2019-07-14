@Override public void endFile(){
  if (sbBatch != null) {
    sendBatch(rows % BATCH_SIZE);
  }
}
