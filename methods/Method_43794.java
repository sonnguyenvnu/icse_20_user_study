private Currency normalise(Currency xchange){
  if (xchange == Currency.BTC) {
    return Currency.XBT;
  }
 else {
    return xchange;
  }
}
