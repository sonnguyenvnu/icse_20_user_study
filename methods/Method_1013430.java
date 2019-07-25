public final synchronized void restart(File file,boolean canRead){
  this.poolFile=file;
  this.isFull=false;
  this.canRead=canRead;
  this.notify();
}
