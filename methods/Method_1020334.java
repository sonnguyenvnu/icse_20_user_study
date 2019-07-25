@Override public XmlSerializer attribute(String namespace,String name,String value) throws IOException, IllegalArgumentException, IllegalStateException {
  append(' ');
  if (namespace != null) {
    append(namespace);
    append(':');
  }
  append(name);
  append("=\"");
  escapeAndAppendString(value);
  append('"');
  mLineStart=false;
  return this;
}
