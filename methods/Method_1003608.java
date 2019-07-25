@Override public ByteBuf touch(Object hint){
  counted.touch();
  delegate.touch(hint);
  return this;
}
