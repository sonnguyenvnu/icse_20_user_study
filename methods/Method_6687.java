public static boolean isWebp(Uri uri){
  InputStream inputStream=null;
  try {
    inputStream=ApplicationLoader.applicationContext.getContentResolver().openInputStream(uri);
    byte[] header=new byte[12];
    if (inputStream.read(header,0,12) == 12) {
      String str=new String(header);
      if (str != null) {
        str=str.toLowerCase();
        if (str.startsWith("riff") && str.endsWith("webp")) {
          return true;
        }
      }
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
 finally {
    try {
      if (inputStream != null) {
        inputStream.close();
      }
    }
 catch (    Exception e2) {
      FileLog.e(e2);
    }
  }
  return false;
}
