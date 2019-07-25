private void find(Value v){
  v=inColumn.convert(v);
  int id=inColumn.getColumnId();
  start.setValue(id,v);
  cursor=index.find(tableFilter,start,start);
}
