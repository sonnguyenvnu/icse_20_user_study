/** 
 * Returns first n bytes of encoded image as hexbytes
 * @param length the number of bytes to return
 */
public String getFirstBytesAsHexString(int length){
  CloseableReference<PooledByteBuffer> imageBuffer=getByteBufferRef();
  if (imageBuffer == null) {
    return "";
  }
  int imageSize=getSize();
  int resultSampleSize=Math.min(imageSize,length);
  byte[] bytesBuffer=new byte[resultSampleSize];
  try {
    PooledByteBuffer pooledByteBuffer=imageBuffer.get();
    if (pooledByteBuffer == null) {
      return "";
    }
    pooledByteBuffer.read(0,bytesBuffer,0,resultSampleSize);
  }
  finally {
    imageBuffer.close();
  }
  StringBuilder stringBuilder=new StringBuilder(bytesBuffer.length * 2);
  for (  byte b : bytesBuffer) {
    stringBuilder.append(String.format("%02X",b));
  }
  return stringBuilder.toString();
}
