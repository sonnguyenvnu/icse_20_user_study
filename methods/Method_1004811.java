public BytesArray pop(){
  Entry entry=entries.remove(0);
  size-=entry.length;
  byte[] entryData=new byte[entry.length];
  System.arraycopy(data.bytes(),entry.offset,entryData,0,entry.length);
  return new BytesArray(entryData,entry.length);
}
