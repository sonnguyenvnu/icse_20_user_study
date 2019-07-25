public final void resume(int increment,DecimalFormat format){
  this.running=true;
  this.increment=increment;
  if (format != null) {
    this.format=format;
  }
}
