/** 
 * Is this a global position?
 * @param pos position
 * @param codeTabIndex index of the code in codeTabs
 * @return true if the position 'pos' is in global scopein the code 'codeTabs[codeTabIndex]'
 */
private boolean isGlobal(int pos,int codeTabIndex){
  return (curlyScopes.get(codeTabIndex)[pos] == 0);
}
