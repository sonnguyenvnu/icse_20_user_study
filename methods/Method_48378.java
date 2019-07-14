public static void writeEntry(DataOutput out,Entry entry){
  VariableLong.writePositive(out,entry.getValuePosition());
  writeBuffer(out,entry);
  if (!entry.hasMetaData())   out.putByte((byte)0);
 else {
    Map<EntryMetaData,Object> metadata=entry.getMetaData();
    assert metadata.size() > 0 && metadata.size() < Byte.MAX_VALUE;
    assert EntryMetaData.values().length < Byte.MAX_VALUE;
    out.putByte((byte)metadata.size());
    for (    Map.Entry<EntryMetaData,Object> metas : metadata.entrySet()) {
      EntryMetaData meta=metas.getKey();
      out.putByte((byte)meta.ordinal());
      out.writeObjectNotNull(metas.getValue());
    }
  }
}
