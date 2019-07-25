public Currency div(int n){
  return new Currency(this.myAmount.divide(new BigDecimal(n)),myCurrency);
}
