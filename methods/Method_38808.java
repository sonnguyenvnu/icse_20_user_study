/** 
 * Returns root Jerry.
 */
public Jerry root(){
  Jerry jerry=this.parent;
  if (jerry == null) {
    return this;
  }
  while (jerry.parent != null) {
    jerry=jerry.parent;
  }
  return jerry;
}
