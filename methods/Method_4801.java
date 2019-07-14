/** 
 * Continues a read from the provided  {@code source} into a given {@code target}. It's assumed that the data should be written into  {@code target} starting from an offset of zero.
 * @param source The source from which to read.
 * @param target The target into which data is to be read, or {@code null} to skip.
 * @param targetLength The target length of the read.
 * @return Whether the target length has been reached.
 */
private boolean continueRead(ParsableByteArray source,byte[] target,int targetLength){
  int bytesToRead=Math.min(source.bytesLeft(),targetLength - bytesRead);
  if (bytesToRead <= 0) {
    return true;
  }
 else   if (target == null) {
    source.skipBytes(bytesToRead);
  }
 else {
    source.readBytes(target,bytesRead,bytesToRead);
  }
  bytesRead+=bytesToRead;
  return bytesRead == targetLength;
}
