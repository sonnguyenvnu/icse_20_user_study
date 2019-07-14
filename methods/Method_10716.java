/** 
 * outputStream?byteArr
 * @param bytes ????
 * @return ????
 */
public static OutputStream bytes2OutputStream(byte[] bytes){
  ByteArrayOutputStream os=null;
  try {
    os=new ByteArrayOutputStream();
    os.write(bytes);
    return os;
  }
 catch (  IOException e) {
    e.printStackTrace();
    return null;
  }
 finally {
    RxFileTool.closeIO(os);
  }
}
