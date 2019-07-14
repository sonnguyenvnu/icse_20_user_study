public static OutputStream getOutputStream(Context context,String str){
  OutputStream outputStream=null;
  Uri fileUri=getUriFromFile(str,context);
  if (fileUri != null) {
    try {
      outputStream=context.getContentResolver().openOutputStream(fileUri);
    }
 catch (    Throwable th) {
    }
  }
  return outputStream;
}
