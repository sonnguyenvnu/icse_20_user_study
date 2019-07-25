/** 
 * ????? ?????? http://www.mybatis.org/generator/reference/pluggingIn.html
 * @param introspectedTable
 */
@Override public void initialized(IntrospectedTable introspectedTable){
  super.initialized(introspectedTable);
  String exampleType=introspectedTable.getExampleType();
  Context context=getContext();
  JavaModelGeneratorConfiguration configuration=context.getJavaModelGeneratorConfiguration();
  String targetPackage=configuration.getTargetPackage();
  String newExampleType=exampleType.replace(targetPackage,this.targetPackage);
  introspectedTable.setExampleType(newExampleType);
  logger.debug("itfsw(Example ???????):??" + introspectedTable.getExampleType() + "???" + this.targetPackage);
}
