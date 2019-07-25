/** 
 * {@inheritDoc} 
 */
@Override public void parsed(ByteArray bytes,int offset,int len,String human){
  if (!suppressDump) {
    super.parsed(bytes,offset,len,human);
  }
}
