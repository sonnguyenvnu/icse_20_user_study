public static Intent makeWebsiteIntent(Context context){
  return WebViewActivity.makeIntent(Uri.parse("https://accounts.douban.com"),context);
}
