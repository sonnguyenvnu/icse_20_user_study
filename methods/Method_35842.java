@Override public Object apply(Object context,Options options) throws IOException {
  Object encodingObj=options.hash.get("encoding");
  String encoding=encodingObj != null ? encodingObj.toString() : "utf-8";
  if (Boolean.TRUE.equals(options.hash.get("decode"))) {
    return decode(context.toString(),encoding);
  }
  return encode(context.toString(),encoding);
}
