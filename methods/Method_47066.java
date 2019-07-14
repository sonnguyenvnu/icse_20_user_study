private static int checkFolder(final File folder,Context context){
  boolean lol=Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
  if (lol) {
    boolean ext=FileUtil.isOnExtSdCard(folder,context);
    if (ext) {
      if (!folder.exists() || !folder.isDirectory()) {
        return 0;
      }
      if (!FileUtil.isWritableNormalOrSaf(folder,context)) {
        return 2;
      }
      return 1;
    }
  }
 else   if (Build.VERSION.SDK_INT == 19) {
    if (FileUtil.isOnExtSdCard(folder,context))     return 1;
  }
  if (FileUtil.isWritable(new File(folder,"DummyFile"))) {
    return 1;
  }
 else {
    return 0;
  }
}
