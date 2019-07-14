public IntDict getOrder(int column){
  StringList list=new StringList(getStringColumn(column));
  return list.getOrder();
}
