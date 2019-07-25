/** 
 * Return the  {@link AnnotationBuilder} when done using the{@link AnnotationConfigurer}. This is useful for method chaining.
 * @return the {@link AnnotationBuilder}
 */
@SuppressWarnings("unchecked") public I and(){
  return (I)getBuilder();
}
