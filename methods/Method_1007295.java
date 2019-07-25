/** 
 * Appends a new entry.
 * @param inner     <code>inner_class_info_index</code>
 * @param outer     <code>outer_class_info_index</code>
 * @param name      <code>inner_name_index</code>
 * @param flags     <code>inner_class_access_flags</code>
 */
public void append(int inner,int outer,int name,int flags){
  byte[] data=get();
  int len=data.length;
  byte[] newData=new byte[len + 8];
  for (int i=2; i < len; ++i)   newData[i]=data[i];
  int n=ByteArray.readU16bit(data,0);
  ByteArray.write16bit(n + 1,newData,0);
  ByteArray.write16bit(inner,newData,len);
  ByteArray.write16bit(outer,newData,len + 2);
  ByteArray.write16bit(name,newData,len + 4);
  ByteArray.write16bit(flags,newData,len + 6);
  set(newData);
}
