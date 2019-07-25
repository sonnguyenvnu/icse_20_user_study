/** 
 * {@inheritDoc} 
 */
@Override public void parsed(ByteArray bytes,int offset,int len,String human){
  offset=bytes.underlyingOffset(offset);
  boolean rawBytes=getRawBytes();
  String hex=rawBytes ? hexDump(offset,len) : "";
  print(twoColumns(hex,human));
  readBytes+=len;
}
