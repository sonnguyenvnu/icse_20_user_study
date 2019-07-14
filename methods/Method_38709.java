/** 
 * Writes stored name to JSON string. Cleans storage.
 */
protected void popName(){
  if (isPushed) {
    if (pushedComma) {
      writeComma();
    }
    String name=pushedName;
    pushedName=null;
    isPushed=false;
    writeName(name);
  }
}
