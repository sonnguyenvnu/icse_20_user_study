/** 
 * Read the next bytes from the buffer.
 * @param startPos the position in the data page
 * @param buff the target buffer
 * @param off the offset in the target buffer
 * @param len the number of bytes to read
 */
void read(int startPos,byte[] buff,int off,int len){
  System.arraycopy(data.getBytes(),startPos,buff,off,len);
}
