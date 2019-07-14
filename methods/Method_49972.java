/** 
 * Append a byte to mMessage.
 */
protected void append(int value){
  mMessage.write(value);
  mPosition++;
}
