/** 
 * Returns byte array of request or response.
 */
public byte[] toByteArray(){
  final Buffer buffer=buffer(true);
  ByteArrayOutputStream baos=new ByteArrayOutputStream(buffer.size());
  try {
    buffer.writeTo(baos);
  }
 catch (  IOException ioex) {
    throw new HttpException(ioex);
  }
  return baos.toByteArray();
}
