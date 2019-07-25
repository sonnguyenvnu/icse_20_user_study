/** 
 * When lazyReading of blocks is turned on, inflate the content of a log block from disk
 */
protected void inflate() throws IOException {
  try {
    content=Optional.of(new byte[(int)this.getBlockContentLocation().get().getBlockSize()]);
    inputStream.seek(this.getBlockContentLocation().get().getContentPositionInLogFile());
    inputStream.readFully(content.get(),0,content.get().length);
    inputStream.seek(this.getBlockContentLocation().get().getBlockEndPos());
  }
 catch (  IOException e) {
    try {
      inflate();
    }
 catch (    IOException io) {
      throw new HoodieIOException("unable to lazily read log block from disk",io);
    }
  }
}
