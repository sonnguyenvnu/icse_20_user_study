public String val(int index){
  int i=index >= 0 ? index : vals.length + index;
  if (i < 0 || i >= vals.length)   return null;
  return this.vals[i];
}
