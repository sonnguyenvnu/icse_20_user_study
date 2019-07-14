@Override public void onReceive(Context context,Intent intent){
  LogUtils.i("Received intent: " + intent);
  String apiV2ApiKey=intent.getStringExtra(EXTRA_API_V2_API_KEY);
  String apiV2ApiSecret=intent.getStringExtra(EXTRA_API_V2_API_SECRET);
  String frodoApiKey=intent.getStringExtra(EXTRA_FRODO_API_KEY);
  String frodoApiSecret=intent.getStringExtra(EXTRA_FRODO_API_SECRET);
  ApiCredentialManager.setApiCredential(apiV2ApiKey,apiV2ApiSecret,frodoApiKey,frodoApiSecret);
}
