/** 
 * Add a range of array elements to the stack.
 */
public void push(KType[] elements,int start,int len){
  assert start >= 0 && len >= 0;
  ensureBufferSpace(len);
  System.arraycopy(elements,start,buffer,elementsCount,len);
  elementsCount+=len;
}
