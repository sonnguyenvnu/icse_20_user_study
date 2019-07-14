/** 
 * Gets the  {@link Symbol}'s annotation info, either from a marker annotation on the symbol, from an accepted annotation on the symbol, or from the list of well-known types.
 */
public AnnotationInfo getMarkerOrAcceptedAnnotation(Symbol sym,VisitorState state){
  String nameStr=sym.flatName().toString();
  AnnotationInfo known=knownTypes.getKnownSafeClasses().get(nameStr);
  if (known != null) {
    return known;
  }
  return getAnnotation(sym,ImmutableSet.copyOf(Sets.union(markerAnnotations,acceptedAnnotations)),state);
}
