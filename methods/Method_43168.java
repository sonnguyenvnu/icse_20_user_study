private boolean isIn(Currency currency,Currency[] currencies){
  for (  Currency cur : currencies)   if (cur.equals(currency))   return true;
  return false;
}
