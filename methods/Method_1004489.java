public void clean(){
  logManager.deleteSegmentsBeforeOffset(committed);
}
