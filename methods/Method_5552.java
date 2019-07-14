/** 
 * Consumes caption data (cc_data), writing the content as samples to all of the provided outputs.
 * @param presentationTimeUs The presentation time in microseconds for any samples.
 * @param ccDataBuffer The buffer containing the caption data.
 * @param outputs The outputs to which any samples should be written.
 */
public static void consumeCcData(long presentationTimeUs,ParsableByteArray ccDataBuffer,TrackOutput[] outputs){
  int firstByte=ccDataBuffer.readUnsignedByte();
  boolean processCcDataFlag=(firstByte & 0x40) != 0;
  if (!processCcDataFlag) {
    return;
  }
  int ccCount=firstByte & 0x1F;
  ccDataBuffer.skipBytes(1);
  int sampleLength=ccCount * 3;
  int sampleStartPosition=ccDataBuffer.getPosition();
  for (  TrackOutput output : outputs) {
    ccDataBuffer.setPosition(sampleStartPosition);
    output.sampleData(ccDataBuffer,sampleLength);
    output.sampleMetadata(presentationTimeUs,C.BUFFER_FLAG_KEY_FRAME,sampleLength,0,null);
  }
}
