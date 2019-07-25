@Override public Object[] extract(Order order){
  BillingInfo billingInfo=order.getBilling();
  return new Object[]{"BILLING:",billingInfo.getPaymentId(),billingInfo.getPaymentDesc()};
}
