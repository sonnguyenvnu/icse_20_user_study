@NotNull @Override public String read(@NotNull Bytes in,long size,@Nullable String using){
  if (0 > size || size > Integer.MAX_VALUE)   throw new IllegalStateException("positive int size expected, " + size + " given");
  sb.setLength(0);
  BytesUtil.parseUtf8(in,sb,(int)size);
  return sb.toString();
}
