/** 
 * Reads the bytes from the InputStream into a byte array.
 * @param is InputStream to read from.
 * @return byte array representation of the input stream.
 * @throws IOException Thrown if the given input stream cannot be read from.
 */
public static byte[] from(InputStream is) throws IOException {
  try (ByteArrayOutputStream buffer=new ByteArrayOutputStream()){
    int r;
    byte[] data=new byte[BUFF_SIZE];
    while ((r=is.read(data,0,data.length)) != -1) {
      buffer.write(data,0,r);
    }
    buffer.flush();
    return buffer.toByteArray();
  }
 }
