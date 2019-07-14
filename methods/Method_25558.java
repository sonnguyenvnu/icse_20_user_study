/** 
 * Returns declaration annotations of the given symbol, as well as 'top-level' type annotations, including : <ul> <li>Type annotations of the return type of a method. <li>Type annotations on the type of a formal parameter or field. </ul> <p>One might expect this to be equivalent to information returned by  {@link Type#getAnnotationMirrors}, but javac doesn't associate type annotation information with types for symbols completed from class files, so that approach doesn't work across compilation boundaries.
 */
public static Stream<Attribute.Compound> getDeclarationAndTypeAttributes(Symbol sym){
  return MoreAnnotations.getDeclarationAndTypeAttributes(sym);
}
