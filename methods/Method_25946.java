private Description fixByModifyingMethod(VisitorState state,JCClassDecl enclosingClass,MethodTree method){
  JCModifiers methodModifiers=((JCMethodDecl)method).getModifiers();
  String replacementModifiersString=createReplacementMethodModifiers(state,methodModifiers);
  JCModifiers enclosingClassModifiers=enclosingClass.getModifiers();
  String enclosingClassReplacementModifiersString=createReplacementClassModifiers(state,enclosingClassModifiers);
  SuggestedFix.Builder fixBuilder=SuggestedFix.builder().addImport("dagger.multibindings.Multibinds").replace(methodModifiers,replacementModifiersString).replace(method.getBody(),";");
  fixBuilder=(enclosingClassModifiers.pos == -1) ? fixBuilder.prefixWith(enclosingClass,enclosingClassReplacementModifiersString) : fixBuilder.replace(enclosingClassModifiers,enclosingClassReplacementModifiersString);
  return describeMatch(method,fixBuilder.build());
}
