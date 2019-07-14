/** 
 * Check for the presence of an annotation, considering annotation inheritance.
 * @return true if the symbol is annotated with given type.
 */
public static boolean hasAnnotation(Symbol sym,Class<? extends Annotation> annotationClass,VisitorState state){
  return hasAnnotation(sym,annotationClass.getName(),state);
}
