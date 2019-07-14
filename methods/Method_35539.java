@Override public ResponseDefinition execute(Admin admin,Request request,PathParams pathParams){
  StubMapping newStubMapping=StubMapping.buildFrom(request.getBodyAsString());
  UUID id=UUID.fromString(pathParams.get("id"));
  SingleStubMappingResult stubMappingResult=admin.getStubMapping(id);
  if (!stubMappingResult.isPresent()) {
    return ResponseDefinition.notFound();
  }
  newStubMapping.setId(id);
  admin.editStubMapping(newStubMapping);
  return ResponseDefinition.okForJson(newStubMapping);
}
