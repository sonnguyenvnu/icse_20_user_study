private static Intent getIntent(Context context,Class<? extends DownloadService> clazz,String action){
  return new Intent(context,clazz).setAction(action);
}
