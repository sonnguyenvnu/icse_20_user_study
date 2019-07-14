@Override public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,IntrospectedTable introspectedTable){
  this.makeSerializable(topLevelClass,introspectedTable);
  return true;
}
