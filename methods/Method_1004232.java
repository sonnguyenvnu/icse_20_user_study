@NotNull @Override public StringBuilder read(Bytes in,long size,@Nullable StringBuilder using){
  if (0 > size || size > Integer.MAX_VALUE)   throw new IllegalStateException("positive int size expected, " + size + " given");
  int csLen=(int)size;
  if (using == null) {
    using=new StringBuilder(csLen);
  }
 else {
    using.setLength(0);
    using.ensureCapacity(csLen);
  }
  BytesUtil.parseUtf8(in,using,csLen);
  return using;
}
