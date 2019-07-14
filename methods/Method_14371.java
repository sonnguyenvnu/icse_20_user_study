final static private InputStream wrapPrefixRemovingInputStream(InputStream inputStream) throws XMLStreamException, IOException {
  PushbackInputStream pis=new PushbackInputStream(inputStream);
  int b;
  int count=0;
  while (count < 100 && (b=pis.read()) >= 0) {
    if (++count > 100) {
      throw new XMLStreamException("File starts with too much non-XML content to skip over");
    }
 else     if (b == '<') {
      pis.unread(b);
      break;
    }
  }
  return pis;
}
