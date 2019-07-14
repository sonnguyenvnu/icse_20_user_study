public static void setApiCredential(String apiV2ApiKey,String apiV2ApiSecret,String frodoApiKey,String frodoApiSecret){
  Settings.API_V2_API_KEY.putValue(apiV2ApiKey);
  Settings.API_V2_API_SECRET.putValue(apiV2ApiSecret);
  ApiCredential.ApiV2.KEY=apiV2ApiKey;
  ApiCredential.ApiV2.SECRET=apiV2ApiSecret;
  Settings.FRODO_API_KEY.putValue(frodoApiKey);
  Settings.FRODO_API_SECRET.putValue(frodoApiSecret);
  ApiCredential.Frodo.KEY=frodoApiKey;
  ApiCredential.Frodo.SECRET=frodoApiSecret;
}
