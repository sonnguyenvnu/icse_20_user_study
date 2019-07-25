@Override public Value[] next(){
  if (cursor == null) {
    cursor=map.cursor(null);
  }
  if (!cursor.hasNext()) {
    return null;
  }
  cursor.next();
  Value[] currentRow=cursor.getValue().getList();
  if (hasEnum) {
    fixEnum(currentRow);
  }
  return currentRow;
}
