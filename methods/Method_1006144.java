/** 
 * Declares global integer variables. It has one argument, a list of variable names. There are two such automatically-declared variables, entry.max$ and global.max$, used for limiting the lengths of string vari- ables. You may have any number of these commands, but a variable's declaration must precede its use.
 * @param child
 */
private void integers(Tree child){
  Tree t=child.getChild(0);
  for (int i=0; i < t.getChildCount(); i++) {
    String name=t.getChild(i).getText();
    integers.put(name,0);
  }
}
