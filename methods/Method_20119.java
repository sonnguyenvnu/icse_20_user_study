/** 
 * Broadcast finished upload (success or failure).
 * @return true if a running receiver received the broadcast.
 */
private boolean broadcastUploadFinished(@Nullable Uri downloadUrl,@Nullable Uri fileUri){
  boolean success=downloadUrl != null;
  String action=success ? UPLOAD_COMPLETED : UPLOAD_ERROR;
  Intent broadcast=new Intent(action).putExtra(EXTRA_DOWNLOAD_URL,downloadUrl).putExtra(EXTRA_FILE_URI,fileUri);
  return LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(broadcast);
}
