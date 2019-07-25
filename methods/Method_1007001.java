@Override public Object[] extract(LineItem item){
  return new Object[]{"ITEM:",item.getItemId(),item.getPrice()};
}
