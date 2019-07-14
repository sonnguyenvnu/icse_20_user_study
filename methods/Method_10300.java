/** 
 * Used when there is no file to be used when calling constructor
 * @param context Context, must not be null
 * @return temporary file or null if creating file failed
 */
protected File getTemporaryFile(Context context){
  Utils.asserts(context != null,"Tried creating temporary file without having Context");
  try {
    return File.createTempFile("temp_","_handled",context.getCacheDir());
  }
 catch (  IOException e) {
    AsyncHttpClient.log.e(LOG_TAG,"Cannot create temporary file",e);
  }
  return null;
}
