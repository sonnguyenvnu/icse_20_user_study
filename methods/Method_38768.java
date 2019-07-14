/** 
 * Shortcut methods for given annotation class.
 */
public static AnnotationParser parserFor(final Class<? extends Annotation> annotationClass){
  return new AnnotationParser(annotationClass,Transaction.class);
}
