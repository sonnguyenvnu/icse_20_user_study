@Override public String format(){
  if (Math.abs(System.currentTimeMillis() - this.last_time) < this.maxCacheDiff)   return this.last_format;
  final long time=System.currentTimeMillis();
synchronized (this.dateFormat) {
    if (Math.abs(time - this.last_time) < this.maxCacheDiff)     return this.last_format;
    this.last_format=this.dateFormat.format(new Date(time));
  }
  this.last_time=time;
  return this.last_format;
}
