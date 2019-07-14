/** 
 * Triggers  {@link Intent#ACTION_MEDIA_SCANNER_SCAN_FILE} intent to refresh the media store.
 * @param uri File's {@link Uri}
 * @param c {@link Context}
 */
private static void scanFile(@NonNull Uri uri,@NonNull Context c){
  Intent mediaScanIntent=new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,uri);
  c.sendBroadcast(mediaScanIntent);
}
