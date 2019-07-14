/** 
 * Gets the  {@link Tree}'s  {@code @Immutable} annotation info, either from an annotation on thesymbol or from the list of well-known immutable types.
 */
AnnotationInfo getImmutableAnnotation(Tree tree,VisitorState state){
  Symbol sym=ASTHelpers.getSymbol(tree);
  return sym == null ? null : threadSafety.getMarkerOrAcceptedAnnotation(sym,state);
}
