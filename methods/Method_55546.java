/** 
 * Sets the current stack pointer. <p>This method directly manipulates the stack pointer. Using it irresponsibly may break the internal state of the stack. It should only be used in rare cases or in auto-generated code.</p>
 */
public void setPointer(int pointer){
  if (CHECKS) {
    checkPointer(pointer);
  }
  this.pointer=pointer;
}
