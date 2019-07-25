@Override public Value[] next(){
  if (cursor == null) {
    cursor=map.cursor(null);
    current=null;
    valueCount=0L;
  }
  if (--valueCount > 0) {
    return current;
  }
  if (!cursor.hasNext()) {
    current=null;
    return null;
  }
  current=getValue(cursor.next().getList());
  if (hasEnum) {
    fixEnum(current);
  }
  valueCount=cursor.getValue();
  return current;
}
