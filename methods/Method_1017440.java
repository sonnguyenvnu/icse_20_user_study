@JSONField(name="tag_id") public MenuMatchRule group(int tagId){
  matchRule.put("tag_id",tagId);
  this.tagId=tagId;
  return this;
}
