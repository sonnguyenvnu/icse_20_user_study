@Override public Description matchLiteral(LiteralTree tree,VisitorState state){
  if (tree.getKind() != STRING_LITERAL) {
    return Description.NO_MATCH;
  }
  if (ASTHelpers.isJUnitTestCode(state)) {
    return Description.NO_MATCH;
  }
  String literal=(String)tree.getValue();
  if (literal == null) {
    return Description.NO_MATCH;
  }
  for (  Map.Entry<String,String> entry : PATH_TABLE.entrySet()) {
    String hardCodedPath=entry.getKey();
    if (!literal.startsWith(hardCodedPath)) {
      continue;
    }
    String correctPath=entry.getValue();
    String remainderPath=literal.substring(hardCodedPath.length());
    SuggestedFix.Builder suggestedFix=SuggestedFix.builder();
    if (remainderPath.isEmpty()) {
      suggestedFix.replace(tree,correctPath);
    }
 else {
      suggestedFix.replace(tree,correctPath + " + \"" + remainderPath + "\"");
    }
    if (correctPath.equals(SDCARD)) {
      suggestedFix.addImport("android.os.Environment");
    }
 else {
      suggestedFix.addImport("android.content.Context");
    }
    return describeMatch(tree,suggestedFix.build());
  }
  return Description.NO_MATCH;
}
