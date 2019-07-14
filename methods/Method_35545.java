@Override public ResponseDefinition execute(Admin admin,Request request,PathParams pathParams){
  StubMapping stubMapping=StubMapping.buildFrom(request.getBodyAsString());
  admin.editStubMapping(stubMapping);
  return ResponseDefinition.noContent();
}
