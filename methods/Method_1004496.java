public void clean(long sequence){
  long offset=sequence * PULL_LOG_UNIT_BYTES;
  logManager.deleteSegmentsBeforeOffset(offset);
}
