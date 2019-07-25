public boolean realloc(int newdqn){
  if (pnlps.length >= newdqn) {
    return true;
  }
  ST_pointnlink_t pnlps2[]=new ST_pointnlink_t[newdqn];
  for (int i=0; i < pnlps.length; i++) {
    pnlps2[i]=pnlps[i];
  }
  this.pnlps=pnlps2;
  return true;
}
