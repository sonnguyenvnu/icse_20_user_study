/** 
 * Efficiently fetch the bytes from the InputStream, provided that caller can guess exact numbers of bytes that can be read from inputStream. Avoids one extra byte[] allocation that ByteStreams.toByteArray() performs.
 * @param hint - size of inputStream's content in bytes
 */
public static byte[] getBytesFromStream(InputStream inputStream,int hint) throws IOException {
  ByteArrayOutputStream byteOutput=new ByteArrayOutputStream(hint){
    @Override public byte[] toByteArray(){
      if (count == buf.length) {
        return buf;
      }
 else {
        return super.toByteArray();
      }
    }
  }
;
  ByteStreams.copy(inputStream,byteOutput);
  return byteOutput.toByteArray();
}
