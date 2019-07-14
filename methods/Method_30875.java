public static Intent makeIntent(Uri uri,Context context){
  Intent intent=new Intent(context,WebViewActivity.class).setData(uri);
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    if (Settings.CREATE_NEW_TASK_FOR_WEBVIEW.getValue()) {
      intent.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT).addFlags(Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
    }
  }
  return intent;
}
