/** 
 * Retrieves the content of a sent file and saves it to a temporary file. The full path to the saved file is returned.
 */
private String saveTmpFile(ByteBuffer b,int offset,int len,String filename_hint){
  String path="";
  if (len > 0) {
    FileOutputStream fileOutputStream=null;
    try {
      ITempFile tempFile=this.tempFileManager.createTempFile(filename_hint);
      ByteBuffer src=b.duplicate();
      fileOutputStream=new FileOutputStream(tempFile.getName());
      FileChannel dest=fileOutputStream.getChannel();
      src.position(offset).limit(offset + len);
      dest.write(src.slice());
      path=tempFile.getName();
    }
 catch (    Exception e) {
      throw new Error(e);
    }
 finally {
      NanoHTTPD.safeClose(fileOutputStream);
    }
  }
  return path;
}
