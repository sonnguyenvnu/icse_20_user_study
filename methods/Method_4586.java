/** 
 * Writes  {@code length} bytes of sample data into {@code target} at {@code offset}, consisting of pending  {@link #sampleStrippedBytes} and any remaining data read from {@code input}.
 */
private void readToTarget(ExtractorInput input,byte[] target,int offset,int length) throws IOException, InterruptedException {
  int pendingStrippedBytes=Math.min(length,sampleStrippedBytes.bytesLeft());
  input.readFully(target,offset + pendingStrippedBytes,length - pendingStrippedBytes);
  if (pendingStrippedBytes > 0) {
    sampleStrippedBytes.readBytes(target,offset,pendingStrippedBytes);
  }
  sampleBytesRead+=length;
}
