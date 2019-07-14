/** 
 * Undefined variables are in the blacklist
 * @param literal
 * @return
 */
private boolean inBlackList(ASTLiteral literal){
  String name=literal.getImage();
  int lineNum=literal.getBeginLine();
  if (name == null) {
    return false;
  }
  for (  String whiteItem : LITERAL_WHITE_LIST) {
    if (whiteItem.equals(name)) {
      return false;
    }
  }
  ASTIfStatement ifStatement=literal.getFirstParentOfType(ASTIfStatement.class);
  if (ifStatement != null && lineNum == ifStatement.getBeginLine()) {
    ASTForStatement forStatement=ifStatement.getFirstParentOfType(ASTForStatement.class);
    ASTWhileStatement whileStatement=ifStatement.getFirstParentOfType(ASTWhileStatement.class);
    return forStatement == null && whileStatement == null;
  }
  ASTForStatement blackForStatement=literal.getFirstParentOfType(ASTForStatement.class);
  if (blackForStatement != null && lineNum == blackForStatement.getBeginLine()) {
    return true;
  }
  ASTWhileStatement blackWhileStatement=literal.getFirstParentOfType(ASTWhileStatement.class);
  return blackWhileStatement != null && lineNum == blackWhileStatement.getBeginLine();
}
