/** 
 * Call when done.
 * @return Returns a unique sentinel value to indicate end-of-iteration.
 */
protected final E done(){
  state=AT_END;
  return null;
}
