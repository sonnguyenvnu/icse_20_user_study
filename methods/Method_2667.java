/** 
 * ???????????MacOS??????ByteArray???
 * @param bytes
 * @param offset
 * @param value
 * @return
 */
public boolean load(byte[] bytes,int offset,V[] value){
  if (bytes == null)   return false;
  size=ByteUtil.bytesHighFirstToInt(bytes,offset);
  offset+=4;
  base=new int[size + 65535];
  check=new int[size + 65535];
  for (int i=0; i < size; i++) {
    base[i]=ByteUtil.bytesHighFirstToInt(bytes,offset);
    offset+=4;
    check[i]=ByteUtil.bytesHighFirstToInt(bytes,offset);
    offset+=4;
  }
  v=value;
  return true;
}
