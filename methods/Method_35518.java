@Override public ResponseDefinition execute(Admin admin,Request request,PathParams pathParams){
  return ResponseDefinition.okForJson(admin.getGlobalSettings());
}
