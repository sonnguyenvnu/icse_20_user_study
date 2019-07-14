/** 
 * Returns true if the default is empty, or contains only a break statement. 
 */
private boolean trivialDefault(List<? extends StatementTree> defaultStatements){
  if (defaultStatements.isEmpty()) {
    return true;
  }
  return (defaultStatements.size() == 1 && getOnlyElement(defaultStatements).getKind() == Tree.Kind.BREAK);
}
