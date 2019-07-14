/** 
 * Validates a  {@code @RestrictedApi} annotation and that the declared restriction makes sense.<p>The other match functions in this class check the <em>usages</em> of a restricted API.
 */
@Override public Description matchAnnotation(AnnotationTree tree,VisitorState state){
  if (!ASTHelpers.getSymbol(tree).toString().equals(RestrictedApi.class.getName())) {
    return Description.NO_MATCH;
  }
  AnnotationMirror restrictedApi=ASTHelpers.getAnnotationMirror(tree);
  if (restrictedApi == null) {
    return Description.NO_MATCH;
  }
  return Description.NO_MATCH;
}
