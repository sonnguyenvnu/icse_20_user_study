/** 
 * Convert an int array to a boolean array. An int equal to zero will return false, and any other value will return true.
 * @return array of boolean elements
 */
static final public boolean[] parseBoolean(int what[]){
  boolean outgoing[]=new boolean[what.length];
  for (int i=0; i < what.length; i++) {
    outgoing[i]=(what[i] != 0);
  }
  return outgoing;
}
