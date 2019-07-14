public File createTempFile(String namePart,int byteSize){
  try {
    File f=File.createTempFile(namePart,"_handled",getCacheDir());
    FileOutputStream fos=new FileOutputStream(f);
    Random r=new Random();
    byte[] buffer=new byte[byteSize];
    r.nextBytes(buffer);
    fos.write(buffer);
    fos.flush();
    fos.close();
    return f;
  }
 catch (  Throwable t) {
    Log.e(LOG_TAG,"createTempFile failed",t);
  }
  return null;
}
