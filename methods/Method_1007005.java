@Override public void write(List<? extends CustomerUpdate> items) throws Exception {
  for (  CustomerUpdate customerUpdate : items) {
    if (customerUpdate.getOperation() == CustomerOperation.ADD) {
      customerDao.insertCustomer(customerUpdate.getCustomerName(),customerUpdate.getCredit());
    }
 else     if (customerUpdate.getOperation() == CustomerOperation.UPDATE) {
      customerDao.updateCustomer(customerUpdate.getCustomerName(),customerUpdate.getCredit());
    }
  }
}
