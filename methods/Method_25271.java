/** 
 * Returns true if all declarations inside the given compilation unit have been visited. 
 */
private boolean finishedCompilation(CompilationUnitTree tree){
  OUTER:   for (  Tree decl : tree.getTypeDecls()) {
switch (decl.getKind()) {
case EMPTY_STATEMENT:
      continue OUTER;
case IMPORT:
    continue OUTER;
default :
  break;
}
if (!seen.contains(decl)) {
return false;
}
}
return true;
}
