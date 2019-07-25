/** 
 * Returns the number of non-null messages 
 */
public int size(){
  int retval=0;
  for (int i=0; i < index; i++)   if (messages[i] != null)   retval++;
  return retval;
}
