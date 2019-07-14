@Override public synchronized Object setMetaData(EntryMetaData key,Object value){
  if (metadata == EntryMetaData.EMPTY_METADATA)   metadata=new EntryMetaData.Map();
  return metadata.put(key,value);
}
