/** 
 * groups catch statements by equivalence class, according to the equivalence  {@link #areEquivalent(ASTCatchStatement,ASTCatchStatement)}. 
 */
private Set<List<ASTCatchStatement>> equivalenceClasses(List<ASTCatchStatement> catches){
  Set<List<ASTCatchStatement>> result=new HashSet<>(catches.size());
  for (  ASTCatchStatement stmt : catches) {
    if (result.isEmpty()) {
      result.add(newEquivClass(stmt));
      continue;
    }
    boolean isNewClass=true;
    for (    List<ASTCatchStatement> equivClass : result) {
      if (areEquivalent(stmt,equivClass.get(0))) {
        equivClass.add(stmt);
        isNewClass=false;
        break;
      }
    }
    if (isNewClass) {
      result.add(newEquivClass(stmt));
    }
  }
  return result;
}
