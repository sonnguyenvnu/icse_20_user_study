public static Uri getContentUri(File file,Context context){
  return ImageTypeFileProvider.getUriForFile(context,BuildConfig.FILE_PROVIDIER_AUTHORITY,file);
}
