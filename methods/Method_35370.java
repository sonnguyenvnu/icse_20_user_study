@Override public String getTextToShowInBubble(int position){
  Song item=getItem(position);
  if (position > 0 && item == null) {
    item=getItem(position - 1);
  }
  return item.getDisplayName().substring(0,1);
}
