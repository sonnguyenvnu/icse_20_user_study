@Override public ResponseDefinition execute(Admin admin,Request request,PathParams pathParams){
  byte[] fileContent=request.getBody();
  FileSource fileSource=admin.getOptions().filesRoot().child(FILES_ROOT);
  fileSource.writeBinaryFile(pathParams.get("filename"),fileContent);
  return ResponseDefinition.okForJson(fileContent);
}
