public static boolean isGif(Uri uri){
  InputStream inputStream=null;
  try {
    inputStream=ApplicationLoader.applicationContext.getContentResolver().openInputStream(uri);
    byte[] header=new byte[3];
    if (inputStream.read(header,0,3) == 3) {
      String str=new String(header);
      if (str != null && str.equalsIgnoreCase("gif")) {
        return true;
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
