private static String getFileRoot(Context context){
  if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
    File external=context.getExternalFilesDir(null);
    if (external != null) {
      return external.getAbsolutePath();
    }
  }
  return context.getFilesDir().getAbsolutePath();
}
