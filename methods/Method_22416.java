/** 
 * Attempt to close each open sketch in preparation for quitting.
 * @return false if canceled along the way
 */
protected boolean handleQuitEach(){
  for (  Editor editor : editors) {
    if (!editor.checkModified()) {
      return false;
    }
  }
  return true;
}
