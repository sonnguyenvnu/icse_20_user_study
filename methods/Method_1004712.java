protected ObjectNode links(ObjectNode parent,List<Type> types,String appUrl){
  ObjectNode content=nodeFactory.objectNode();
  types.forEach((it) -> content.set(it.getId(),link(appUrl,it)));
  parent.set("_links",content);
  return content;
}
