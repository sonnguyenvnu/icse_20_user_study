@Override public Description matchMethod(MethodTree tree,VisitorState state){
  MultiMatchResult<AnnotationTree> qualifierAnnotations=QUALIFIER_ANNOTATION_FINDER.multiMatchResult(tree,state);
  MultiMatchResult<AnnotationTree> injectAnnotations=HAS_INJECT.multiMatchResult(tree,state);
  if (!(qualifierAnnotations.matches() && injectAnnotations.matches())) {
    return Description.NO_MATCH;
  }
  Builder fixBuilder=SuggestedFix.builder();
  List<AnnotationTree> matchingAnnotations=qualifierAnnotations.matchingNodes();
  if (ASTHelpers.getSymbol(tree).isConstructor()) {
    List<AnnotationTree> scopes=new ArrayList<>();
    List<AnnotationTree> qualifiers=new ArrayList<>();
    for (    AnnotationTree annoTree : matchingAnnotations) {
      (IS_SCOPING_ANNOTATION.matches(annoTree,state) ? scopes : qualifiers).add(annoTree);
    }
    ClassTree outerClass=ASTHelpers.findEnclosingNode(state.getPath(),ClassTree.class);
    scopes.forEach(a -> {
      fixBuilder.delete(a);
      fixBuilder.prefixWith(outerClass,state.getSourceForNode(a) + " ");
    }
);
    deleteAll(qualifiers,fixBuilder);
    return describeMatch(tree,fixBuilder.build());
  }
  if (PROVIDES_METHOD.matches(tree,state)) {
    deleteAll(injectAnnotations.matchingNodes(),fixBuilder);
    return describeMatch(injectAnnotations.matchingNodes().get(0),fixBuilder.build());
  }
  deleteAll(matchingAnnotations,fixBuilder);
  return describeMatch(matchingAnnotations.get(0),fixBuilder.build());
}
