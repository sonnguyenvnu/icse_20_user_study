/** 
 * ??????sdcard.
 * @param context
 * @param dbName ????? ?? xx.db
 */
public static void exportDb2Sdcard(Context context,String dbName){
  String filePath=context.getDatabasePath(dbName).getAbsolutePath();
  byte[] buffer=new byte[FILE_BUFFER];
  int length;
  OutputStream output;
  InputStream input;
  try {
    input=new FileInputStream(new File((filePath)));
    output=new FileOutputStream(context.getExternalCacheDir() + File.separator + dbName);
    while ((length=input.read(buffer)) > 0) {
      output.write(buffer,0,length);
    }
    output.flush();
    output.close();
    input.close();
    Log.i(TAG,"mv success!");
  }
 catch (  IOException e) {
    Log.e(TAG,e.toString());
  }
}
