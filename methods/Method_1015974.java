public byte[] load() throws IOException {
  if (this.inputStream == null)   return null;
  ByteArrayOutputStream baos=new ByteArrayOutputStream();
  int count;
  byte[] buffer=new byte[2048];
  try {
    while ((count=this.inputStream.read(buffer)) > 0)     baos.write(buffer,0,count);
  }
 catch (  IOException e) {
    Data.logger.warn(e.getMessage());
  }
 finally {
    this.close();
  }
  return baos.toByteArray();
}
