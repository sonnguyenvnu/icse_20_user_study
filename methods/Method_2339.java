protected void makeSerializable(TopLevelClass topLevelClass,IntrospectedTable introspectedTable){
  if (this.addGWTInterface) {
    topLevelClass.addImportedType(this.gwtSerializable);
    topLevelClass.addSuperInterface(this.gwtSerializable);
  }
  if (!this.suppressJavaInterface) {
    topLevelClass.addImportedType(this.serializable);
    topLevelClass.addSuperInterface(this.serializable);
    Field field=new Field();
    field.setFinal(true);
    field.setInitializationString("1L");
    field.setName("serialVersionUID");
    field.setStatic(true);
    field.setType(new FullyQualifiedJavaType("long"));
    field.setVisibility(JavaVisibility.PRIVATE);
    this.context.getCommentGenerator().addFieldComment(field,introspectedTable);
    topLevelClass.addField(field);
  }
}
