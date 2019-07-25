public QueryBuilderScopeContext collect(){
  QueryBuilderScopeContext qb=new QueryBuilderScopeContext();
  Map<String,String> map=this.findRootDefinition(methodReferences);
  if (map.size() > 0) {
    Map.Entry<String,String> entry=map.entrySet().iterator().next();
    qb.addTable(entry.getKey(),entry.getValue());
  }
  for (  MethodReference methodReference : methodReferences) {
    String name=methodReference.getName();
    if (name != null) {
      collectParameter(qb,methodReference,name);
      collectJoins(qb,methodReference,name);
      collectSelects(qb,methodReference,name);
      collectSelectInForm(qb,methodReference,name);
    }
  }
  if (qb.getTableMap().size() > 0) {
    Map.Entry<String,String> entry=qb.getTableMap().entrySet().iterator().next();
    String className=entry.getKey();
    PhpClass phpClass=PhpElementsUtil.getClassInterface(project,className);
    if (phpClass != null) {
      qb.addPropertyAlias(entry.getValue(),new QueryBuilderPropertyAlias(entry.getValue(),null,new DoctrineModelField(entry.getValue()).addTarget(phpClass).setTypeName(phpClass.getPresentableFQN())));
      List<QueryBuilderRelation> relationList=new ArrayList<>();
      for (      DoctrineModelField field : EntityHelper.getModelFields(phpClass)) {
        qb.addPropertyAlias(entry.getValue() + "." + field.getName(),new QueryBuilderPropertyAlias(entry.getValue(),field.getName(),field));
        if (field.getRelation() != null && field.getRelationType() != null) {
          relationList.add(new QueryBuilderRelation(field.getName(),field.getRelation()));
        }
      }
      qb.addRelation(entry.getValue(),relationList);
    }
    QueryBuilderRelationClassResolver resolver=new QueryBuilderRelationClassResolver(project,entry.getValue(),entry.getKey(),qb.getRelationMap(),qb.getJoinMap());
    resolver.collect();
  }
  this.buildPropertyMap(qb);
  return qb;
}
