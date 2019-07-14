private File getDiskCacheDir(){
  String cachePath;
  if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
    File externalCacheDir=listener.getSimpleActivity().getExternalCacheDir();
    if (externalCacheDir != null) {
      cachePath=externalCacheDir.getPath();
    }
 else {
      cachePath=listener.getSimpleActivity().getCacheDir().getPath();
    }
  }
 else {
    cachePath=listener.getSimpleActivity().getCacheDir().getPath();
  }
  return new File(cachePath,imageName());
}
