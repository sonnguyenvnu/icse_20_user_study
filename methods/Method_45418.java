final RestSetting toRestSetting(){
  if (response == null) {
    throw new IllegalArgumentException("Response is expected in rest setting");
  }
  return this.getRestSettingBuilder().response(response.getResponseHandler());
}
