/** 
 * Returns a copy of this input handler that shares the same key bindings. Setting key bindings in the copy will also set them in the original.
 */
public InputHandler copy(){
  return new DefaultInputHandler(this);
}
