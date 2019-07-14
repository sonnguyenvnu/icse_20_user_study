/** 
 * Check for the presence of an annotation with a specific simple name directly on this symbol. Does *not* consider annotation inheritance.
 * @param sym the symbol to check for the presence of the annotation
 * @param simpleName the simple name of the annotation to look for, e.g. "Nullable" or"CheckReturnValue"
 */
public static boolean hasDirectAnnotationWithSimpleName(Symbol sym,String simpleName){
  for (  AnnotationMirror annotation : sym.getAnnotationMirrors()) {
    if (annotation.getAnnotationType().asElement().getSimpleName().contentEquals(simpleName)) {
      return true;
    }
  }
  return false;
}
