@PostMapping("entity") public String withEntity(HttpEntity<String> entity){
  return "Posted request body '" + entity.getBody() + "'; headers = " + entity.getHeaders();
}
