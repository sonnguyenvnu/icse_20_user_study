@SuppressWarnings("PMD.LocalVariableNamingConventions") private void replaceItems(final int idx,long value,int start,final int delta){
  start+=idx;
  long $;
  do {
    $=this.cache[start];
    this.cache[start]=value;
    value=$;
    start+=delta;
  }
 while (value != 0);
}
