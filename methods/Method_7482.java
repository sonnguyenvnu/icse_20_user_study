public void bindByteBuffer(int index,ByteBuffer value) throws SQLiteException {
  bindByteBuffer(sqliteStatementHandle,index,value,value.limit());
}
