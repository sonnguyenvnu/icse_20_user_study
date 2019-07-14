public BigDecimal getAmount(){
  if (isTrade()) {
    if (getCurrencyPair().base == Currency.BTC) {
      return btc;
    }
 else     if (getCurrencyPair().base == Currency.XRP) {
      return xrp;
    }
 else     if (getCurrencyPair().base == Currency.LTC) {
      return ltc;
    }
 else     if (getCurrencyPair().base == Currency.ETH) {
      return eth;
    }
 else     if (getCurrencyPair().base == Currency.BCH) {
      return bch;
    }
 else {
      return BigDecimal.ZERO;
    }
  }
 else   if (btc.signum() != 0) {
    return btc;
  }
 else   if (bch.signum() != 0) {
    return bch;
  }
 else   if (xrp.signum() != 0) {
    return xrp;
  }
 else   if (ltc.signum() != 0) {
    return ltc;
  }
 else   if (eth.signum() != 0) {
    return eth;
  }
 else   if (gbp.signum() != 0) {
    return gbp;
  }
 else   if (usd.signum() != 0) {
    return usd;
  }
 else   if (eur.signum() != 0) {
    return eur;
  }
 else {
    return BigDecimal.ZERO;
  }
}
