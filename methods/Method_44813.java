@Override public long getOrderId(){
  if (payment.orderChanges.size() == 1) {
    return payment.orderChanges.get(0).getSequence();
  }
 else {
    return 0;
  }
}
