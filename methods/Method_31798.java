/** 
 * Gets a hash code for the duration that is compatible with the  equals method.
 * @return a hash code
 */
public int hashCode(){
  long len=getMillis();
  return (int)(len ^ (len >>> 32));
}
