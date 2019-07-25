private Order process(Order order,Customer customer,List<ShoppingCartItem> items,OrderTotalSummary summary,Payment payment,Transaction transaction,MerchantStore store) throws ServiceException {
  Validate.notNull(order,"Order cannot be null");
  Validate.notNull(customer,"Customer cannot be null (even if anonymous order)");
  Validate.notEmpty(items,"ShoppingCart items cannot be null");
  Validate.notNull(payment,"Payment cannot be null");
  Validate.notNull(store,"MerchantStore cannot be null");
  Validate.notNull(summary,"Order total Summary cannot be null");
  Set<OrderProduct> products=order.getOrderProducts();
  for (  OrderProduct orderProduct : products) {
    orderProduct.getProductQuantity();
    Product p=productService.getByCode(orderProduct.getSku(),store.getDefaultLanguage());
    if (p == null)     throw new ServiceException(ServiceException.EXCEPTION_INVENTORY_MISMATCH);
    for (    ProductAvailability availability : p.getAvailabilities()) {
      int qty=availability.getProductQuantity();
      if (qty < orderProduct.getProductQuantity()) {
        throw new ServiceException(ServiceException.EXCEPTION_INVENTORY_MISMATCH);
      }
      qty=qty - orderProduct.getProductQuantity();
      availability.setProductQuantity(qty);
    }
    productService.update(p);
  }
  Transaction processTransaction=paymentService.processPayment(customer,store,payment,items,order);
  if (order.getOrderHistory() == null || order.getOrderHistory().size() == 0 || order.getStatus() == null) {
    OrderStatus status=order.getStatus();
    if (status == null) {
      status=OrderStatus.ORDERED;
      order.setStatus(status);
    }
    Set<OrderStatusHistory> statusHistorySet=new HashSet<OrderStatusHistory>();
    OrderStatusHistory statusHistory=new OrderStatusHistory();
    statusHistory.setStatus(status);
    statusHistory.setDateAdded(new Date());
    statusHistory.setOrder(order);
    statusHistorySet.add(statusHistory);
    order.setOrderHistory(statusHistorySet);
  }
  if (customer.getId() == null || customer.getId() == 0) {
    customerService.create(customer);
  }
  order.setCustomerId(customer.getId());
  this.create(order);
  if (transaction != null) {
    transaction.setOrder(order);
    if (transaction.getId() == null || transaction.getId() == 0) {
      transactionService.create(transaction);
    }
 else {
      transactionService.update(transaction);
    }
  }
  if (processTransaction != null) {
    processTransaction.setOrder(order);
    if (processTransaction.getId() == null || processTransaction.getId() == 0) {
      transactionService.create(processTransaction);
    }
 else {
      transactionService.update(processTransaction);
    }
  }
  return order;
}
