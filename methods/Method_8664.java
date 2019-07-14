public boolean isLastInRow(int i){
  checkLayout();
  return itemsToRow.get(i,Integer.MAX_VALUE) != Integer.MAX_VALUE;
}
