private static String getDefaultCaseContents(CaseTree defaultCase,List<? extends StatementTree> defaultStatements,VisitorState state){
  CharSequence sourceCode=state.getSourceCode();
  if (sourceCode == null) {
    return "";
  }
  String defaultSource=sourceCode.subSequence(((JCTree)defaultStatements.get(0)).getStartPosition(),state.getEndPosition(getLast(defaultStatements))).toString();
  String initialComments=comments(defaultCase,defaultStatements,sourceCode);
  return initialComments + defaultSource;
}
