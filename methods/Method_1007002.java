private void process(FieldSet fieldSet) throws Exception {
  if (fieldSet == null) {
    log.debug("FINISHED");
    recordFinished=true;
    order=null;
    return;
  }
  String lineId=fieldSet.readString(0);
  if (Order.LINE_ID_HEADER.equals(lineId)) {
    log.debug("STARTING NEW RECORD");
    order=headerMapper.mapFieldSet(fieldSet);
  }
 else   if (Order.LINE_ID_FOOTER.equals(lineId)) {
    log.debug("END OF RECORD");
    order.setTotalPrice(fieldSet.readBigDecimal("TOTAL_PRICE"));
    order.setTotalLines(fieldSet.readInt("TOTAL_LINE_ITEMS"));
    order.setTotalItems(fieldSet.readInt("TOTAL_ITEMS"));
    recordFinished=true;
  }
 else   if (Customer.LINE_ID_BUSINESS_CUST.equals(lineId)) {
    log.debug("MAPPING CUSTOMER");
    if (order.getCustomer() == null) {
      Customer customer=customerMapper.mapFieldSet(fieldSet);
      customer.setBusinessCustomer(true);
      order.setCustomer(customer);
    }
  }
 else   if (Customer.LINE_ID_NON_BUSINESS_CUST.equals(lineId)) {
    log.debug("MAPPING CUSTOMER");
    if (order.getCustomer() == null) {
      Customer customer=customerMapper.mapFieldSet(fieldSet);
      customer.setBusinessCustomer(false);
      order.setCustomer(customer);
    }
  }
 else   if (Address.LINE_ID_BILLING_ADDR.equals(lineId)) {
    log.debug("MAPPING BILLING ADDRESS");
    order.setBillingAddress(addressMapper.mapFieldSet(fieldSet));
  }
 else   if (Address.LINE_ID_SHIPPING_ADDR.equals(lineId)) {
    log.debug("MAPPING SHIPPING ADDRESS");
    order.setShippingAddress(addressMapper.mapFieldSet(fieldSet));
  }
 else   if (BillingInfo.LINE_ID_BILLING_INFO.equals(lineId)) {
    log.debug("MAPPING BILLING INFO");
    order.setBilling(billingMapper.mapFieldSet(fieldSet));
  }
 else   if (ShippingInfo.LINE_ID_SHIPPING_INFO.equals(lineId)) {
    log.debug("MAPPING SHIPPING INFO");
    order.setShipping(shippingMapper.mapFieldSet(fieldSet));
  }
 else   if (LineItem.LINE_ID_ITEM.equals(lineId)) {
    log.debug("MAPPING LINE ITEM");
    if (order.getLineItems() == null) {
      order.setLineItems(new ArrayList<>());
    }
    order.getLineItems().add(itemMapper.mapFieldSet(fieldSet));
  }
 else {
    log.debug("Could not map LINE_ID=" + lineId);
  }
}
