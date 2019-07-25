@PostConstruct private void init(){
  amount=new BigDecimal(0);
  paymentOption=PaymentTypeEnum.DEBIT;
}
