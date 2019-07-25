public static boolean save(File file,InputStream inputStream){
  OutputStream out=null;
  try {
    out=new FileOutputStream(file);
    int read;
    byte[] bytes=new byte[1024];
    while ((read=inputStream.read(bytes)) != -1) {
      out.write(bytes,0,read);
    }
    return true;
  }
 catch (  IOException e) {
    Log.e(Gh4Application.LOG_TAG,e.getMessage(),e);
  }
 finally {
    try {
      if (inputStream != null) {
        inputStream.close();
      }
      if (out != null) {
        out.flush();
        out.close();
      }
    }
 catch (    IOException e) {
      Log.e(Gh4Application.LOG_TAG,e.getMessage(),e);
    }
  }
  return false;
}
