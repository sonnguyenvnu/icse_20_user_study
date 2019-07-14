public static Balance convert(OkexFundingAccountRecord rec){
  return new Balance.Builder().currency(Currency.getInstance(rec.getCurrency())).available(rec.getAvailable()).frozen(rec.getBalance().subtract(rec.getAvailable())).total(rec.getBalance()).build();
}
