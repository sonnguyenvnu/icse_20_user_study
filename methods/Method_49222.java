public StandardRelationTypeMaker name(String name){
  SystemTypeManager.throwIfSystemName(getSchemaCategory(),name);
  this.name=name;
  return this;
}
