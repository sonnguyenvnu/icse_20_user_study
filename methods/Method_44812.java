@Override public long getOrderId(){
  if (orderChanges.size() == 1) {
    return orderChanges.get(0).getSequence();
  }
 else {
    return 0;
  }
}
