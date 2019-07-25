@NotNull @Override public CharSequence read(@NotNull Bytes in,long size,@Nullable CharSequence using){
  if (0 > size || size > Integer.MAX_VALUE)   throw new IllegalStateException("positive int size expected, " + size + " given");
  int csLen=(int)size;
  StringBuilder usingSB;
  if (using instanceof StringBuilder) {
    usingSB=((StringBuilder)using);
    usingSB.setLength(0);
    usingSB.ensureCapacity(csLen);
  }
 else {
    usingSB=new StringBuilder(csLen);
  }
  BytesUtil.parseUtf8(in,usingSB,csLen);
  return usingSB;
}
