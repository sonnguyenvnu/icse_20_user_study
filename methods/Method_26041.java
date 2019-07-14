private static String messageForAnnos(List<AnnotationTree> annotationTrees){
  String annoNames=annotationTrees.stream().map(a -> Signatures.prettyType(ASTHelpers.getType(a))).collect(Collectors.joining(" and "));
  return annoNames + " can only be applied to static methods.";
}
