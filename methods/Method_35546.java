@Override public ResponseDefinition execute(Admin admin,Request request,PathParams pathParams){
  StubMapping removeMapping=StubMapping.buildFrom(request.getBodyAsString());
  admin.removeStubMapping(removeMapping);
  return ResponseDefinition.ok();
}
