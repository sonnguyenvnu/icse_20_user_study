/** 
 * Outputs up to  {@code length} bytes of sample data to {@code output}, consisting of either {@link #sampleStrippedBytes} or data read from {@code input}.
 */
private int readToOutput(ExtractorInput input,TrackOutput output,int length) throws IOException, InterruptedException {
  int bytesRead;
  int strippedBytesLeft=sampleStrippedBytes.bytesLeft();
  if (strippedBytesLeft > 0) {
    bytesRead=Math.min(length,strippedBytesLeft);
    output.sampleData(sampleStrippedBytes,bytesRead);
  }
 else {
    bytesRead=output.sampleData(input,length,false);
  }
  sampleBytesRead+=bytesRead;
  sampleBytesWritten+=bytesRead;
  return bytesRead;
}
