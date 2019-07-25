public long convert(final TimeUnit unit){
  return unit.convert(this.duration,this.unit);
}
