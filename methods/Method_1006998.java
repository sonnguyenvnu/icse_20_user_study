@Override public Object[] extract(Order order){
  Customer customer=order.getCustomer();
  return new Object[]{"CUSTOMER:",customer.getRegistrationId(),emptyIfNull(customer.getFirstName()),emptyIfNull(customer.getMiddleName()),emptyIfNull(customer.getLastName())};
}
