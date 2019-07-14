/** 
 * Registers new TX annotations.
 */
@SuppressWarnings({"unchecked"}) public void registerAnnotations(final Class<? extends Annotation>[] annotations){
  this.annotations=annotations;
  this.annotationParsers=new AnnotationParser[annotations.length];
  for (int i=0; i < annotations.length; i++) {
    annotationParsers[i]=TransactionAnnotationValues.parserFor(annotations[i]);
  }
}
