private int parseMetaData(Map<EntryMetaData,Object> metadata,int baseOffset){
  assert hasMetaData();
  for (  EntryMetaData meta : metaDataSchema) {
    MetaDataSerializer s=getSerializer(meta);
    Object d=s.read(data,baseOffset);
    baseOffset+=s.getByteLength(d);
    metadata.put(meta,d);
  }
  return baseOffset;
}
