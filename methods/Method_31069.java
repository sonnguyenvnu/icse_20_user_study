public static void openWithWebViewActivity(Uri uri,Context context){
  context.startActivity(WebViewActivity.makeIntent(uri,context));
}
