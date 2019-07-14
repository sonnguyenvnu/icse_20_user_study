/** 
 * Called to pass stream data. The data passed should not include the 3 byte start code.
 * @param data Holds the data being passed.
 * @param offset The offset of the data in {@code data}.
 * @param limit The limit (exclusive) of the data in {@code data}.
 */
public void appendToNalUnit(byte[] data,int offset,int limit){
  if (!isFilling) {
    return;
  }
  int readLength=limit - offset;
  if (nalData.length < nalLength + readLength) {
    nalData=Arrays.copyOf(nalData,(nalLength + readLength) * 2);
  }
  System.arraycopy(data,offset,nalData,nalLength,readLength);
  nalLength+=readLength;
}
