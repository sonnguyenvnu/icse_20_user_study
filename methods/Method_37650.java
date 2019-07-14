/** 
 * Returns a hashcode for this value.
 */
@Override public int hashCode(){
  return (int)(value ^ (value >>> 32));
}
