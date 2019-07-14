/** 
 * Gets the  {@link Symbol}'s  {@code @Immutable} annotation info, either from an annotation on thesymbol or from the list of well-known immutable types.
 */
AnnotationInfo getImmutableAnnotation(Symbol sym,VisitorState state){
  String nameStr=sym.flatName().toString();
  AnnotationInfo known=wellKnownMutability.getKnownImmutableClasses().get(nameStr);
  if (known != null) {
    return known;
  }
  return threadSafety.getInheritedAnnotation(sym,state);
}
