private Integer getIntegerFromPartColumn(Cursor c,int columnIndex){
  if (!c.isNull(columnIndex)) {
    return c.getInt(columnIndex);
  }
  return null;
}
