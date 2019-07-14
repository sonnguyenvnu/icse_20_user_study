void initialize(Iterable<Currency> currencies){
  currencies.forEach(currency -> balances.put(currency,new AtomicReference<>(new Balance(currency,ZERO))));
}
