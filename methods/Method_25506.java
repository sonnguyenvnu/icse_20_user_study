private static boolean isGenerated(Symbol sym,VisitorState state){
  for (  String annotation : GENERATED_ANNOTATIONS) {
    if (ASTHelpers.hasAnnotation(sym,annotation,state)) {
      return true;
    }
  }
  return false;
}
