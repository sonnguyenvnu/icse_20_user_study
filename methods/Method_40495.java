/** 
 * Clears the module table, discarding all resolved ASTs (modules) and their scope information.
 */
public void clearModuleTable(){
  moduleTable.clear();
  moduleTable=new Scope(null,Scope.ScopeType.GLOBAL);
  clearAstCache();
}
