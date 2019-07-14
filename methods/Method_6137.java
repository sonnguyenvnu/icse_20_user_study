public static boolean isDownloadsDocument(Uri uri){
  return "com.android.providers.downloads.documents".equals(uri.getAuthority());
}
