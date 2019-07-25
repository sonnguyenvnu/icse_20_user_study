protected ObjectNode link(String appUrl,Type type){
  ObjectNode result=nodeFactory.objectNode();
  result.put("href",generateTemplatedUri(appUrl,type));
  result.put("templated",true);
  return result;
}
