@Override protected void validate(List<ProxyOptionParser> parsers){
  if (parsers.size() != 1) {
    throw new XpipeRuntimeException("Proxy ReqResp Protocol only send one option");
  }
}
