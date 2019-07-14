/** 
 * Check for the presence of an annotation, considering annotation inheritance.
 * @return true if the tree is annotated with given type.
 */
public static boolean hasAnnotation(Tree tree,Class<? extends Annotation> annotationClass,VisitorState state){
  return hasAnnotation(tree,annotationClass.getName(),state);
}
