/** 
 * This method draws canvas again for Undo.
 * @return If Undo is enabled, this is returned as true. Otherwise, this is returned as false.
 */
public boolean undo(){
  if (this.historyPointer > 1) {
    this.historyPointer--;
    this.invalidate();
    return true;
  }
 else {
    return false;
  }
}
