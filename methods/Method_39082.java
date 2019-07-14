/** 
 * Scans annotation and returns type of Madvoc annotations.
 */
public Class<? extends Annotation> detectAnnotationType(final Annotation[] annotations){
  for (  final Annotation annotation : annotations) {
    if (annotation instanceof In) {
      return annotation.annotationType();
    }
 else     if (annotation instanceof Out) {
      return annotation.annotationType();
    }
  }
  return null;
}
