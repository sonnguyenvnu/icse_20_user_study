public void load(byte[] data,int dataOffset,RandomAccessData variableData,int variableOffset,JarEntryFilter filter) throws IOException {
  this.header=data;
  this.headerOffset=dataOffset;
  long nameLength=Bytes.littleEndianValue(data,dataOffset + 28,2);
  long extraLength=Bytes.littleEndianValue(data,dataOffset + 30,2);
  long commentLength=Bytes.littleEndianValue(data,dataOffset + 32,2);
  this.localHeaderOffset=Bytes.littleEndianValue(data,dataOffset + 42,4);
  dataOffset+=46;
  if (variableData != null) {
    data=Bytes.get(variableData.getSubsection(variableOffset + 46,nameLength + extraLength + commentLength));
    dataOffset=0;
  }
  this.name=new AsciiBytes(data,dataOffset,(int)nameLength);
  if (filter != null) {
    this.name=filter.apply(this.name);
  }
  this.extra=NO_EXTRA;
  this.comment=NO_COMMENT;
  if (extraLength > 0) {
    this.extra=new byte[(int)extraLength];
    System.arraycopy(data,(int)(dataOffset + nameLength),this.extra,0,this.extra.length);
  }
  if (commentLength > 0) {
    this.comment=new AsciiBytes(data,(int)(dataOffset + nameLength + extraLength),(int)commentLength);
  }
}
