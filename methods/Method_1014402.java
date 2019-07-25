/** 
 * get number of pages in this buffer
 * @return number of pages
 */
public int length(){
  return byteBuffer.limit() / PAGE_SIZE;
}
