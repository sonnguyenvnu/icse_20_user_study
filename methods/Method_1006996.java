@Override public Object[] extract(Order order){
  Address address=order.getBillingAddress();
  return new Object[]{"ADDRESS:",address.getAddrLine1(),address.getCity(),address.getZipCode()};
}
