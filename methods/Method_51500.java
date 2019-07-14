@Override public void start() throws IOException {
  String encoding=getProperty(ENCODING);
  if ("utf-8".equalsIgnoreCase(encoding)) {
    useUTF8=true;
  }
  Writer writer=getWriter();
  StringBuilder buf=new StringBuilder(500);
  buf.append("<?xml version=\"1.0\" encoding=\"" + encoding + "\"?>").append(PMD.EOL);
  createVersionAttr(buf);
  createTimestampAttr(buf);
  buf.append('>').append(PMD.EOL);
  writer.write(buf.toString());
}
