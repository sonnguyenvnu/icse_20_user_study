public static String getDatabasePath(Context context){
  File webViewDirectory=context.getDir("webview",Context.MODE_PRIVATE);
  File databasesDirectory=new File(webViewDirectory,"databases");
  if (!databasesDirectory.exists()) {
    databasesDirectory.mkdir();
  }
  return databasesDirectory.getPath();
}
