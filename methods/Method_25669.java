/** 
 * Checks that annotations are on the right side of the modifiers. 
 */
private Description checkAnnotations(Tree tree,List<? extends AnnotationTree> annotations,Comment danglingJavadoc,int firstModifierPos,int lastModifierPos,VisitorState state){
  SuggestedFix.Builder builder=SuggestedFix.builder();
  List<AnnotationTree> moveBefore=new ArrayList<>();
  List<AnnotationTree> moveAfter=new ArrayList<>();
  boolean annotationProblem=false;
  for (  AnnotationTree annotation : annotations) {
    int annotationPos=((JCTree)annotation).getStartPosition();
    if (annotationPos <= firstModifierPos) {
      continue;
    }
    AnnotationType annotationType=ASTHelpers.getAnnotationType(annotation,getSymbol(tree),state);
    if (annotationPos >= lastModifierPos) {
      if (tree instanceof ClassTree || annotationType == AnnotationType.DECLARATION) {
        annotationProblem=true;
        moveBefore.add(annotation);
      }
    }
 else {
      annotationProblem=true;
      if (tree instanceof ClassTree || annotationType == AnnotationType.DECLARATION || annotationType == null) {
        moveBefore.add(annotation);
      }
 else {
        moveAfter.add(annotation);
      }
    }
  }
  if (annotationProblem) {
    for (    AnnotationTree annotation : moveBefore) {
      builder.delete(annotation);
    }
    for (    AnnotationTree annotation : moveAfter) {
      builder.delete(annotation);
    }
    String javadoc=danglingJavadoc == null ? "" : removeJavadoc(state,danglingJavadoc,builder);
    builder.replace(firstModifierPos,firstModifierPos,String.format("%s%s ",javadoc,joinSource(state,moveBefore))).replace(lastModifierPos,lastModifierPos,String.format("%s ",joinSource(state,moveAfter)));
    ImmutableList<String> names=annotations.stream().map(ASTHelpers::getSymbol).filter(Objects::nonNull).map(Symbol::getSimpleName).map(a -> "@" + a).collect(toImmutableList());
    String flattened=names.stream().collect(joining(", "));
    String isAre=names.size() > 1 ? "are not type annotations" : "is not a type annotation";
    String message=String.format("%s %s, so should appear before any modifiers and after Javadocs.",flattened,isAre);
    return buildDescription(tree).setMessage(message).addFix(builder.build()).build();
  }
  return NO_MATCH;
}
