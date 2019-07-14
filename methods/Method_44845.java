private AtomicReference<Balance> defaultBalance(Currency currency){
  return new AtomicReference<>(new Balance(currency,ZERO));
}
