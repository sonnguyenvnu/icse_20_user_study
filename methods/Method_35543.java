@Override public ResponseDefinition execute(Admin admin,Request request,PathParams pathParams){
  GlobalSettings newSettings;
  try {
    newSettings=Json.read(request.getBodyAsString(),GlobalSettings.class);
  }
 catch (  Exception e) {
    newSettings=Json.read(request.getBodyAsString(),GetGlobalSettingsResult.class).getSettings();
  }
  admin.updateGlobalSettings(newSettings);
  return ResponseDefinition.ok();
}
