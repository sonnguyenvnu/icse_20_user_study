private void update(List<LimitOrder> asks,LimitOrder limitOrder){
  int idx=Collections.binarySearch(asks,limitOrder);
  if (idx >= 0) {
    asks.remove(idx);
    asks.add(idx,limitOrder);
  }
 else {
    asks.add(-idx - 1,limitOrder);
  }
}
