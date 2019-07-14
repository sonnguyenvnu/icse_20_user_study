/** 
 * Adds a string to the MDAG.
 * @param str the String to be added to the MDAG
 */
public void addString(String str){
  if (sourceNode != null) {
    addStringInternal(str);
    replaceOrRegister(sourceNode,str);
  }
 else {
    unSimplify();
    addString(str);
  }
}
