/** 
 * Gets the possibly inherited marker annotation on the given symbol, and reverse-propagates containerOf spec's from super-classes.
 */
public AnnotationInfo getInheritedAnnotation(Symbol sym,VisitorState state){
  return getAnnotation(sym,markerAnnotations,state);
}
