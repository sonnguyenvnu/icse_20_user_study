/** 
 * get the underlying bytes, copied
 * @return
 */
public byte[] bytes(){
  return Arrays.copyOf(buf,count);
}
