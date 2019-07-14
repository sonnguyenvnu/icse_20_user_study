public static Intent makeSetApiKeyIntent(Context context){
  return WebViewActivity.makeIntent(Uri.parse("https://github.com/zhanghai/DouyaApiKey/releases/latest"),context);
}
