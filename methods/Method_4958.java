/** 
 * Performs in-place removal of unsynchronization for  {@code length} bytes starting from{@link ParsableByteArray#getPosition()}
 * @param data Contains the data to be processed.
 * @param length The length of the data to be processed.
 * @return The length of the data after processing.
 */
private static int removeUnsynchronization(ParsableByteArray data,int length){
  byte[] bytes=data.data;
  for (int i=data.getPosition(); i + 1 < length; i++) {
    if ((bytes[i] & 0xFF) == 0xFF && bytes[i + 1] == 0x00) {
      System.arraycopy(bytes,i + 2,bytes,i + 1,length - i - 2);
      length--;
    }
  }
  return length;
}
