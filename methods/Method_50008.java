private byte[] getByteArrayFromPartColumn(Cursor c,int columnIndex){
  if (!c.isNull(columnIndex)) {
    return getBytes(c.getString(columnIndex));
  }
  return null;
}
