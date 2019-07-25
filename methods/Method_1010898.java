public String init(SNode node,JComponent editorComponent){
  this.myExpression=node;
  this.myContainer=findContainer(node);
  findDuplicates();
  SNode expressionType=this.getExpressionType(node);
  if (!(SNodeOperations.isInstanceOf(expressionType,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c37f506dL,"jetbrains.mps.baseLanguage.structure.Type")))) {
    return "Couldn't compute type of expression: " + expressionType;
  }
 else   if (this.isVoidType(expressionType)) {
    return "Expression has no type";
  }
 else {
    this.myExpressionType=SNodeOperations.cast(expressionType,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c37f506dL,"jetbrains.mps.baseLanguage.structure.Type"));
    List<String> expectedNames=ListSequence.fromList(new ArrayList<String>());
    String expectedVariableName=null;
    if (SNodeOperations.isInstanceOf(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c37f506fL,"jetbrains.mps.baseLanguage.structure.Expression"))) {
      expectedVariableName=((String)BHReflection.invoke0(SNodeOperations.cast(node,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c37f506fL,"jetbrains.mps.baseLanguage.structure.Expression")),MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c37f506fL,"jetbrains.mps.baseLanguage.structure.Expression"),SMethodTrimmedId.create("getVariableExpectedName",null,"hEwJgm_")));
    }
    if (expectedVariableName != null) {
      ListSequence.fromList(expectedNames).addElement(NameUtil.decapitalize(expectedVariableName));
    }
    List<String> variableSuffixes=((List<String>)BHReflection.invoke0(myExpressionType,MetaAdapterFactory.getConcept(0xf3061a5392264cc5L,0xa443f952ceaf5816L,0xf8c37f506dL,"jetbrains.mps.baseLanguage.structure.Type"),SMethodTrimmedId.create("getVariableSuffixes",null,"hEwIzNo")));
    if (variableSuffixes != null) {
      ListSequence.fromList(expectedNames).addSequence(ListSequence.fromList(variableSuffixes));
    }
    this.myExpectedNames=ListSequence.fromList(expectedNames).where(new IWhereFilter<String>(){
      public boolean accept(      String it){
        return it.matches("[a-zA-Z0-9_]*");
      }
    }
).toListSequence();
    if (ListSequence.fromList(this.myExpectedNames).isEmpty()) {
      ListSequence.fromList(this.myExpectedNames).addElement("");
    }
    return null;
  }
}
