public BigDecimal getPrice(){
  if (isTrade()) {
    if (!Objects.equals(btc_gbp,BigDecimal.ZERO)) {
      return btc_gbp;
    }
 else     if (!Objects.equals(btc_usd,BigDecimal.ZERO)) {
      return btc_usd;
    }
 else     if (!Objects.equals(btc_eur,BigDecimal.ZERO)) {
      return btc_eur;
    }
 else     if (!Objects.equals(bch_gbp,BigDecimal.ZERO)) {
      return bch_gbp;
    }
 else     if (!Objects.equals(eth_gbp,BigDecimal.ZERO)) {
      return eth_gbp;
    }
 else     if (!Objects.equals(xrp_gbp,BigDecimal.ZERO)) {
      return xrp_gbp;
    }
 else     if (!Objects.equals(ltc_gbp,BigDecimal.ZERO)) {
      return ltc_gbp;
    }
  }
  return BigDecimal.ZERO;
}
