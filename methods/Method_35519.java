@Override public ResponseDefinition execute(Admin admin,Request request,PathParams pathParams){
  StubImport stubImport=Json.read(request.getBodyAsString(),StubImport.class);
  admin.importStubs(stubImport);
  return ResponseDefinition.ok();
}
