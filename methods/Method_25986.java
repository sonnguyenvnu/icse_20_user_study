private static SuggestedFix fixClass(ClassTree classTree,VisitorState state){
  int startPos=((JCTree)classTree).getStartPosition();
  int endPos=((JCTree)classTree.getMembers().get(0)).getStartPosition();
  List<ErrorProneToken> tokens=state.getOffsetTokens(startPos,endPos);
  String modifiers=getSymbol(classTree).owner.enclClass() == null ? "final class" : "static final class";
  SuggestedFix.Builder fix=SuggestedFix.builder();
  for (  ErrorProneToken token : tokens) {
    if (token.kind() == TokenKind.INTERFACE) {
      fix.replace(token.pos(),token.endPos(),modifiers);
    }
  }
  return fix.build();
}
