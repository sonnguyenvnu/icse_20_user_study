@Override public ResponseDefinition execute(Admin admin,Request request,PathParams pathParams){
  ExtendedSettingsWrapper extendedSettingsWrapper=Json.read(request.getBodyAsString(),ExtendedSettingsWrapper.class);
  Parameters newExtended=extendedSettingsWrapper.getExtended();
  GlobalSettings existingSettings=admin.getGlobalSettings().getSettings();
  Parameters existingExtended=existingSettings.getExtended();
  Parameters extended=existingExtended.merge(newExtended);
  GlobalSettings newGlobalSettings=existingSettings.copy().extended(extended).build();
  admin.updateGlobalSettings(newGlobalSettings);
  return ResponseDefinition.okEmptyJson();
}
