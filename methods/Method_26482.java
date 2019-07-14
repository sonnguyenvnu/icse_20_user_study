/** 
 * Returns the comments between the "default:" case and the first statement within it, if any. 
 */
private static String comments(CaseTree defaultCase,List<? extends StatementTree> defaultStatements,CharSequence sourceCode){
  if (defaultStatements.isEmpty()) {
    return "";
  }
  int defaultStart=((JCTree)defaultCase).getStartPosition();
  int statementStart=((JCTree)defaultStatements.get(0)).getStartPosition();
  String defaultAndComments=sourceCode.subSequence(defaultStart,statementStart).toString();
  String comments=defaultAndComments.substring(defaultAndComments.indexOf("default:") + "default:".length()).trim();
  if (!comments.isEmpty()) {
    comments="\n" + comments + "\n";
  }
  return comments;
}
