@Override public ResponseDefinition execute(Admin admin,Request request,PathParams pathParams){
  return ResponseDefinition.redirectTo(ADMIN_CONTEXT_ROOT + "/");
}
