public static LoanInfo adaptPoloniexLoans(HashMap<String,PoloniexLoan[]> poloniexLoans){
  Map<String,List<LoanOrder>> loans=new HashMap<>();
  for (  Map.Entry<String,PoloniexLoan[]> item : poloniexLoans.entrySet()) {
    List<LoanOrder> loanOrders=new ArrayList<>();
    for (    PoloniexLoan poloniexLoan : item.getValue()) {
      Date date=PoloniexUtils.stringToDate(poloniexLoan.getDate());
      loanOrders.add(new FixedRateLoanOrder(OrderType.ASK,poloniexLoan.getCurrency(),poloniexLoan.getAmount(),poloniexLoan.getRange(),poloniexLoan.getId(),date,poloniexLoan.getRate()));
    }
    loans.put(item.getKey(),loanOrders);
  }
  return new LoanInfo(loans.get("provided"),loans.get("used"));
}
