@Override public MutableHeaders copy(Headers headers){
  this.headers.add(headers.getNettyHeaders());
  return this;
}
