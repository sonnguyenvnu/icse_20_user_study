@Override public ResponseDefinition execute(Admin admin,Request request,PathParams pathParams){
  String idString=pathParams.get("id");
  UUID id=UUID.fromString(idString);
  SingleServedStubResult result=admin.getServedStub(id);
  return result.isPresent() ? ResponseDefinition.okForJson(result.getItem()) : ResponseDefinition.notFound();
}
