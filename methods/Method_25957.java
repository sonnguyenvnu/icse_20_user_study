/** 
 * Matches an annotation that has an argument for at least one of the given parameters. 
 */
private static Matcher<AnnotationTree> hasAnyParameter(String... parameters){
  return anyOf(transform(asList(parameters),new Function<String,Matcher<AnnotationTree>>(){
    @Override public Matcher<AnnotationTree> apply(    String parameter){
      return hasArgumentWithValue(parameter,Matchers.<ExpressionTree>anything());
    }
  }
));
}
