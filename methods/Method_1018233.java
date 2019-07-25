/** 
 * Copy the contents of this kim to a byte array.
 * @param bytes A byte array of sufficient size.
 * @param at The position within the byte array to take the byes.
 * @return The position immediately after the copy.
 */
public int copy(byte[] bytes,int at){
  System.arraycopy(this.bytes,0,bytes,at,this.length);
  return at + this.length;
}
