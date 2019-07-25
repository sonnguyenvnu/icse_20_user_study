public final synchronized void wakeup(){
  this.canRead=true;
  this.notify();
}
