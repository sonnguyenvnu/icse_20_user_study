@Override public Object apply(Object context,Options options) throws IOException {
  String value=options.tagType == TagType.SECTION ? options.fn(context).toString() : context.toString();
  return value.trim();
}
