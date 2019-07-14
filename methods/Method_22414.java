/** 
 * Open a sketch in a new window.
 * @param path Path to the pde file for the sketch in question
 * @return the Editor object, so that properties (like 'untitled')can be set by the caller
 */
public Editor handleOpen(String path,boolean untitled){
  return handleOpen(path,untitled,new EditorState(editors));
}
