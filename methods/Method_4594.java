/** 
 * Resets the reader to start reading a new variable-length integer.
 */
public void reset(){
  state=STATE_BEGIN_READING;
  length=0;
}
