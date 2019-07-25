public final void resume(DecimalFormat format){
  this.running=true;
  if (format != null) {
    this.format=format;
  }
}
