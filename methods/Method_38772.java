/** 
 * Finds TX annotation.
 */
protected TransactionAnnotationValues readTransactionAnnotation(final Method method){
  for (  AnnotationParser annotationParser : annotationParsers) {
    TransactionAnnotationValues tad=TransactionAnnotationValues.of(annotationParser,method);
    if (tad != null) {
      return tad;
    }
  }
  return null;
}
