/** 
 * Removes the  {@code nth} entry.  It does not eliminateconstant pool items that the removed entry refers to. {@link ClassFile#compact()} should be executed to removethese unnecessary items. 
 * @param nth       0, 1, 2, ...
 * @return  the number of items after the removal.
 * @see ClassFile#compact()
 */
public int remove(int nth){
  byte[] data=get();
  int len=data.length;
  if (len < 10)   return 0;
  int n=ByteArray.readU16bit(data,0);
  int nthPos=2 + nth * 8;
  if (n <= nth)   return n;
  byte[] newData=new byte[len - 8];
  ByteArray.write16bit(n - 1,newData,0);
  int i=2, j=2;
  while (i < len)   if (i == nthPos)   i+=8;
 else   newData[j++]=data[i++];
  set(newData);
  return n - 1;
}
