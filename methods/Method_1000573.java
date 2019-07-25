public Object parse(Reader reader){
  return new JsonTokenScan(reader).read();
}
