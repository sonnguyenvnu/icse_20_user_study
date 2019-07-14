public void bindByteBuffer(int index,NativeByteBuffer value) throws SQLiteException {
  bindByteBuffer(sqliteStatementHandle,index,value.buffer,value.limit());
}
