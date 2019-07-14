@Override public Description matchAnnotation(AnnotationTree tree,VisitorState state){
  RequiredModifiers annotation=ASTHelpers.getAnnotation(tree,RequiredModifiers.class);
  if (annotation == null) {
    return Description.NO_MATCH;
  }
  Set<Modifier> requiredModifiers=ImmutableSet.copyOf(annotation.value());
  if (requiredModifiers.isEmpty()) {
    return Description.NO_MATCH;
  }
  Tree parent=state.getPath().getParentPath().getLeaf();
  if (!(parent instanceof ModifiersTree)) {
    return Description.NO_MATCH;
  }
  Set<Modifier> missing=Sets.difference(requiredModifiers,((ModifiersTree)parent).getFlags());
  if (missing.isEmpty()) {
    return Description.NO_MATCH;
  }
  String annotationName=ASTHelpers.getAnnotationName(tree);
  String nameString=annotationName != null ? String.format("The annotation '@%s'",annotationName) : "This annotation";
  String customMessage=String.format(MESSAGE_TEMPLATE,nameString,missing);
  return buildDescription(tree).addFix(SuggestedFixes.addModifiers(state.getPath().getParentPath().getParentPath().getLeaf(),(ModifiersTree)parent,state,missing)).setMessage(customMessage).build();
}
