/** 
 * Creates a TypeExtractor that extracts the type of a class field if that field is annotated with any one of the given annotations.
 */
public static TypeExtractor<VariableTree> fieldAnnotatedWithOneOf(Stream<String> annotationClasses){
  return extractType(Matchers.allOf(Matchers.isField(),Matchers.anyOf(annotationClasses.map(Matchers::hasAnnotation).collect(Collectors.toList()))));
}
