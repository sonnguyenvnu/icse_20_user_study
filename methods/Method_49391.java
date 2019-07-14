Statement insertColumn(final StaticBuffer key,final Entry entry,final long timestamp){
  final Integer ttl=(Integer)entry.getMetaData().get(EntryMetaData.TTL);
  if (ttl != null) {
    return this.insertColumnWithTTL.bind().setBytes(KEY_BINDING,key.asByteBuffer()).setBytes(COLUMN_BINDING,entry.getColumn().asByteBuffer()).setBytes(VALUE_BINDING,entry.getValue().asByteBuffer()).setLong(TIMESTAMP_BINDING,timestamp).setInt(TTL_BINDING,ttl);
  }
  return this.insertColumn.bind().setBytes(KEY_BINDING,key.asByteBuffer()).setBytes(COLUMN_BINDING,entry.getColumn().asByteBuffer()).setBytes(VALUE_BINDING,entry.getValue().asByteBuffer()).setLong(TIMESTAMP_BINDING,timestamp);
}
