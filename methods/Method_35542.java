@Override public ResponseDefinition execute(Admin admin,Request request,PathParams pathParams){
  String idString=pathParams.get("id");
  UUID id=UUID.fromString(idString);
  SingleStubMappingResult stubMappingResult=admin.getStubMapping(id);
  return stubMappingResult.isPresent() ? ResponseDefinition.okForJson(stubMappingResult.getItem()) : ResponseDefinition.notFound();
}
