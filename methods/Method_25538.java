/** 
 * Check for the presence of an annotation, considering annotation inheritance.
 * @param annotationClass the binary class name of the annotation (e.g."javax.annotation.Nullable", or "some.package.OuterClassName$InnerClassName")
 * @return true if the tree is annotated with given type.
 */
public static boolean hasAnnotation(Tree tree,String annotationClass,VisitorState state){
  Symbol sym=getDeclaredSymbol(tree);
  return hasAnnotation(sym,annotationClass,state);
}
