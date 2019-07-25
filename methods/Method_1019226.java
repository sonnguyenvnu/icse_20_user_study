public static String decompress(byte[] bytes){
  InputStream in=new InflaterInputStream(new ByteArrayInputStream(bytes));
  ByteArrayOutputStream baos=new ByteArrayOutputStream();
  try {
    byte[] buffer=new byte[8192];
    int len;
    while ((len=in.read(buffer)) > 0) {
      baos.write(buffer,0,len);
    }
    return new String(baos.toByteArray(),"UTF-8");
  }
 catch (  IOException e) {
    throw new HoodieIOException("IOException while decompressing text",e);
  }
}
