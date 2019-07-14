/** 
 * Returns an estimate of the number of additional bytes that can be written to the audio track's buffer without running out of space. <p>May only be called if the output encoding is one of the PCM encodings.
 * @param writtenBytes The number of bytes written to the audio track so far.
 * @return An estimate of the number of bytes that can be written.
 */
public int getAvailableBufferSize(long writtenBytes){
  int bytesPending=(int)(writtenBytes - (getPlaybackHeadPosition() * outputPcmFrameSize));
  return bufferSize - bytesPending;
}
