public double freq(String key){
  Integer f=get(key);
  if (f == null)   f=0;
  return f / (double)total;
}
