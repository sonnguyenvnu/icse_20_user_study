@Override public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass,IntrospectedTable introspectedTable){
  this.makeSerializable(topLevelClass,introspectedTable);
  return true;
}
