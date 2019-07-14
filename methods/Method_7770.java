public int getId(int position){
  position-=2;
  if (accountsShowed) {
    position-=getAccountRowsCount();
  }
  if (position < 0 || position >= items.size()) {
    return -1;
  }
  Item item=items.get(position);
  return item != null ? item.id : -1;
}
