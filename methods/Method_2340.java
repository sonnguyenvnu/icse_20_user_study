/** 
 * ???Example???????
 * @param topLevelClass
 * @param introspectedTable
 * @return
 */
@Override public boolean modelExampleClassGenerated(TopLevelClass topLevelClass,IntrospectedTable introspectedTable){
  makeSerializable(topLevelClass,introspectedTable);
  for (  InnerClass innerClass : topLevelClass.getInnerClasses()) {
    if ("GeneratedCriteria".equals(innerClass.getType().getShortName())) {
      innerClass.addSuperInterface(serializable);
    }
    if ("Criteria".equals(innerClass.getType().getShortName())) {
      innerClass.addSuperInterface(serializable);
    }
    if ("Criterion".equals(innerClass.getType().getShortName())) {
      innerClass.addSuperInterface(serializable);
    }
  }
  return true;
}
