/** 
 * Prevents a mapping from the specified class name to another name.
 */
public void fix(String name){
  String name2=toJvmName(name);
  super.put(name2,name2);
}
