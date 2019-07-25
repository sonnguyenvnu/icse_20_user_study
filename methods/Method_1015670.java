/** 
 * The number of non-null keys 
 */
public int length(){
  if (keys == null)   return 0;
  int retval=0;
  for (  byte[] key : keys)   if (key != null)   retval++;
  return retval;
}
