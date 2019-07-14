public static Entry readEntry(ReadBuffer in,Serializer serializer){
  long valuePosition=VariableLong.readPositive(in);
  Preconditions.checkArgument(valuePosition > 0 && valuePosition <= Integer.MAX_VALUE);
  StaticBuffer buffer=readBuffer(in);
  StaticArrayEntry entry=new StaticArrayEntry(buffer,(int)valuePosition);
  int metaSize=in.getByte();
  for (int i=0; i < metaSize; i++) {
    EntryMetaData meta=EntryMetaData.values()[in.getByte()];
    entry.setMetaData(meta,serializer.readObjectNotNull(in,meta.getDataType()));
  }
  return entry;
}
