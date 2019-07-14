Statement deleteColumn(final StaticBuffer key,final StaticBuffer column,final long timestamp){
  return this.deleteColumn.bind().setBytes(KEY_BINDING,key.asByteBuffer()).setBytes(COLUMN_BINDING,column.asByteBuffer()).setLong(TIMESTAMP_BINDING,timestamp);
}
