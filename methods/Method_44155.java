private static void demoRecurringPayments(CoinbaseAccountService accountService) throws IOException {
  CoinbaseRecurringPayments recurringPayments=accountService.getCoinbaseRecurringPayments();
  System.out.println(recurringPayments);
  List<CoinbaseRecurringPayment> recurringPaymentsList=recurringPayments.getRecurringPayments();
  if (!recurringPaymentsList.isEmpty()) {
    CoinbaseRecurringPayment recurringPayment=recurringPaymentsList.get(0);
    recurringPayment=accountService.getCoinbaseRecurringPayment(recurringPayment.getId());
    System.out.println(recurringPayment);
  }
}
