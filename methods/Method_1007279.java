/** 
 * Moves to the given index. <p>The index of the next instruction is set to the given index. The successive call to <code>next()</code> returns the index that has been given to <code>move()</code>. <p>Note that the index is into the byte array returned by <code>get().getCode()</code>.
 * @see CodeAttribute#getCode()
 */
public void move(int index){
  currentPos=index;
}
