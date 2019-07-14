@Override public String toString(){
  return pair == null ? "" : String.format("%s%s",pair.base.getCurrencyCode(),pair.counter.getCurrencyCode());
}
