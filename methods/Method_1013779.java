@Override public ByteBuf format(){
  StringBuilder proxyProtocol=new StringBuilder(ProxyProtocol.KEY_WORD).append(WHITE_SPACE);
  for (  ProxyOptionParser parser : parsers) {
    proxyProtocol.append(parser.output());
    if (parser.isImportant()) {
      proxyProtocol.append(IMPORTANT_OPTION_SIGN);
    }
    proxyProtocol.append(";");
  }
  return new SimpleStringParser(proxyProtocol.toString()).format();
}
