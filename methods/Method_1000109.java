public long exchange(long from,boolean isTRX){
  long relay=exchange_to_supply(isTRX,from);
  return exchange_from_supply(!isTRX,relay);
}
