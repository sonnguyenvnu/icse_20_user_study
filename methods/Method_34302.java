@Override protected ResponseEntity<?> enhanceResponse(ResponseEntity<?> response,Exception exception){
  HttpHeaders headers=response.getHeaders();
  String existing=null;
  if (headers.containsKey("WWW-Authenticate")) {
    existing=extractTypePrefix(headers.getFirst("WWW-Authenticate"));
  }
  StringBuilder builder=new StringBuilder();
  builder.append(typeName + " ");
  builder.append("realm=\"" + realmName + "\"");
  if (existing != null) {
    builder.append(", " + existing);
  }
  HttpHeaders update=new HttpHeaders();
  update.putAll(response.getHeaders());
  update.set("WWW-Authenticate",builder.toString());
  return new ResponseEntity<Object>(response.getBody(),update,response.getStatusCode());
}
