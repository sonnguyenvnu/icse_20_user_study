public static Intent makeIntent(Uri uri,Context context){
  return new Intent(context,UriHandlerActivity.class).setData(uri);
}
