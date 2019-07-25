/** 
 * Returns the number of non-null elements in range [low .. high-1]
 * @return
 */
public int size(){
  int retval=0;
  for (long i=low, num_iterations=0; i < high && num_iterations < buffer.length; i++, num_iterations++) {
    int index=index(i);
    if (buffer[index] != null)     retval++;
  }
  return retval;
}
