public Map reload(){
  attributes.putAll(collection().findOne(map("_id",attributes.get("_id"))).toMap());
  return attributes;
}
