/** 
 * This method draws canvas again for Redo.
 * @return If Redo is enabled, this is returned as true. Otherwise, this is returned as false.
 */
public boolean redo(){
  if (this.historyPointer < this.pathLists.size()) {
    this.historyPointer++;
    this.invalidate();
    return true;
  }
 else {
    return false;
  }
}
