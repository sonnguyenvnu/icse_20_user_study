/** 
 * Returns the table index for the counter at the specified depth.
 * @param item the element's hash
 * @param i the counter depth
 * @return the table index
 */
int indexOf(int item,int i){
  long hash=SEED[i] * item;
  hash+=hash >>> 32;
  return ((int)hash) & tableMask;
}
