public static byte[] compress(String text){
  ByteArrayOutputStream baos=new ByteArrayOutputStream();
  try {
    OutputStream out=new DeflaterOutputStream(baos);
    out.write(text.getBytes("UTF-8"));
    out.close();
  }
 catch (  IOException e) {
    throw new HoodieIOException("IOException while compressing text " + text,e);
  }
  return baos.toByteArray();
}
