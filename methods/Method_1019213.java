@Override public HoodieLogBlock next(){
  try {
    return readBlock();
  }
 catch (  IOException io) {
    throw new HoodieIOException("IOException when reading logblock from log file " + logFile,io);
  }
}
