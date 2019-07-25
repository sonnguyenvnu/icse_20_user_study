private void init(){
  this.dirty=false;
  this.lo=this.curr=this.hi=0;
synchronized (mu) {
    this.buff=(numAvailBuffs > 0) ? availBuffs[--numAvailBuffs] : new byte[BuffSz];
  }
  this.maxHi=BuffSz;
  this.hitEOF=false;
  this.diskPos=0L;
}
