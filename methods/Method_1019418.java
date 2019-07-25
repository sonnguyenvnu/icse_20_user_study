/** 
 * Returns size of IP range.
 * @return Size of IP range.
 */
public long size(){
  return (endIP.getValue() - startIP.getValue() + 1L);
}
