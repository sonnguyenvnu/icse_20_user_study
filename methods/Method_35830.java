@Override public Object apply(Object context,Options options) throws IOException {
  String value=options.tagType == TagType.SECTION ? options.fn(context).toString() : context.toString();
  if (Boolean.TRUE.equals(options.hash.get("decode"))) {
    return new String(decodeBase64(value));
  }
  return encodeBase64(value.getBytes());
}
