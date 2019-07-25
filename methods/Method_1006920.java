@Override public void open(ExecutionContext executionContext) throws ItemStreamException {
  super.open(executionContext);
  if (streamsRegistered) {
    stream.open(executionContext);
    ChunkMonitorData data=new ChunkMonitorData(executionContext.getInt(getExecutionContextKey(OFFSET),0),0);
    holder.set(data);
    if (reader == null) {
      logger.warn("No ItemReader set (must be concurrent step), so ignoring offset data.");
      return;
    }
    for (int i=0; i < data.offset; i++) {
      try {
        reader.read();
      }
 catch (      Exception e) {
        throw new ItemStreamException("Could not position reader with offset: " + data.offset,e);
      }
    }
    resetOffset();
  }
}
