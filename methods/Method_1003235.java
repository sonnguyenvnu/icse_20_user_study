/** 
 * Copy a number of bytes to the given buffer from the current position. The current position is incremented accordingly.
 * @param buff the output buffer
 * @param off the offset in the output buffer
 * @param len the number of bytes to copy
 */
public void read(byte[] buff,int off,int len){
  System.arraycopy(data,pos,buff,off,len);
  pos+=len;
}
