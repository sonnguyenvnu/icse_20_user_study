@Override public int getItemCount(){
  int itemCount=super.getItemCount();
  if (isFooterEnabled()) {
    itemCount+=1;
  }
  return itemCount;
}
