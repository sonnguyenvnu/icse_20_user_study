public <T extends Average>T add(long num){
  if (num < 0)   return (T)this;
  if (Util.productGreaterThan(count,(long)Math.ceil(avg),Long.MAX_VALUE))   clear();
  double total=count * avg;
  avg=(total + num) / ++count;
  return (T)this;
}
