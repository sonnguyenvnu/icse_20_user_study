@Override protected void execute(){
  context.nodeSubtype=TypeSpec.classBuilder(context.className).addAnnotation(AnnotationSpec.builder(SuppressWarnings.class).addMember("value","{$S, $S, $S, $S}","unchecked","PMD.UnusedFormalParameter","MissingOverride","NullAway").build()).addJavadoc(getJavaDoc()).addTypeVariable(kTypeVar).addTypeVariable(vTypeVar);
  if (context.isFinal) {
    context.nodeSubtype.addModifiers(Modifier.FINAL);
  }
  if (isBaseClass()) {
    context.nodeSubtype.superclass(NODE).addSuperinterface(NODE_FACTORY);
  }
 else {
    context.nodeSubtype.superclass(context.superClass);
  }
}
