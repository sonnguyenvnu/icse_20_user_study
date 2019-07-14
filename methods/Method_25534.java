/** 
 * Determines whether a symbol has an annotation of the given type. This includes annotations inherited from superclasses due to  {@code @Inherited}.
 * @param annotationClass the binary class name of the annotation (e.g."javax.annotation.Nullable", or "some.package.OuterClassName$InnerClassName")
 * @return true if the symbol is annotated with given type.
 */
public static boolean hasAnnotation(Symbol sym,String annotationClass,VisitorState state){
  if (sym == null) {
    return false;
  }
  annotationClass=annotationClass.replace('$','.');
  Name annotationName=state.getName(annotationClass);
  if (hasAttribute(sym,annotationName)) {
    return true;
  }
  if (isInherited(state,annotationClass)) {
    while (sym instanceof ClassSymbol) {
      if (hasAttribute(sym,annotationName)) {
        return true;
      }
      sym=((ClassSymbol)sym).getSuperclass().tsym;
    }
  }
  return false;
}
