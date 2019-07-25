/** 
 * Undo the last action for the ClassNode with the given name. To receive changes re-use  {@link #getClass(String)}.
 * @param name
 * @throws IOException
 */
public void undo(String name){
  history.get(name).pop();
}
