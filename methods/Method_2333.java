/** 
 * ???Example???limit?offset???set?get??
 */
@Override public boolean modelExampleClassGenerated(TopLevelClass topLevelClass,IntrospectedTable introspectedTable){
  PrimitiveTypeWrapper integerWrapper=FullyQualifiedJavaType.getIntInstance().getPrimitiveTypeWrapper();
  Field limit=new Field();
  limit.setName("limit");
  limit.setVisibility(JavaVisibility.PRIVATE);
  limit.setType(integerWrapper);
  topLevelClass.addField(limit);
  Method setLimit=new Method();
  setLimit.setVisibility(JavaVisibility.PUBLIC);
  setLimit.setName("setLimit");
  setLimit.addParameter(new Parameter(integerWrapper,"limit"));
  setLimit.addBodyLine("this.limit = limit;");
  topLevelClass.addMethod(setLimit);
  Method getLimit=new Method();
  getLimit.setVisibility(JavaVisibility.PUBLIC);
  getLimit.setReturnType(integerWrapper);
  getLimit.setName("getLimit");
  getLimit.addBodyLine("return limit;");
  topLevelClass.addMethod(getLimit);
  Field offset=new Field();
  offset.setName("offset");
  offset.setVisibility(JavaVisibility.PRIVATE);
  offset.setType(integerWrapper);
  topLevelClass.addField(offset);
  Method setOffset=new Method();
  setOffset.setVisibility(JavaVisibility.PUBLIC);
  setOffset.setName("setOffset");
  setOffset.addParameter(new Parameter(integerWrapper,"offset"));
  setOffset.addBodyLine("this.offset = offset;");
  topLevelClass.addMethod(setOffset);
  Method getOffset=new Method();
  getOffset.setVisibility(JavaVisibility.PUBLIC);
  getOffset.setReturnType(integerWrapper);
  getOffset.setName("getOffset");
  getOffset.addBodyLine("return offset;");
  topLevelClass.addMethod(getOffset);
  return true;
}
