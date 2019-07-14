@Override public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass,IntrospectedTable introspectedTable){
  this.makeSerializable(topLevelClass,introspectedTable);
  return true;
}
