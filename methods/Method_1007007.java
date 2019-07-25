@Override public void write(List<? extends Trade> trades){
  for (  Trade trade : trades) {
    CustomerDebit customerDebit=new CustomerDebit();
    customerDebit.setName(trade.getCustomer());
    customerDebit.setDebit(trade.getPrice());
    dao.write(customerDebit);
  }
}
