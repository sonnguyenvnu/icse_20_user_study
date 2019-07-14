public double freq(char... keyArray){
  Integer f=d.get(keyArray);
  if (f == null)   f=0;
  return f / (double)total;
}
