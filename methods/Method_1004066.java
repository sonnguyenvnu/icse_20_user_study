/** 
 * Moves  {@link #cursor} to next instruction.
 */
final void next(){
  if (cursor == null) {
    return;
  }
  cursor=cursor.getNext();
  skipNonOpcodes();
}
