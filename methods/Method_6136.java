public static boolean isExternalStorageDocument(Uri uri){
  return "com.android.externalstorage.documents".equals(uri.getAuthority());
}
