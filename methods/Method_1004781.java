public void process(BytesArray storage){
  if (ObjectUtils.isEmpty(paths)) {
    return;
  }
  results.clear();
  if (log.isTraceEnabled()) {
    log.trace(String.format("About to look for paths [%s] in doc [%s]",Arrays.toString(paths),storage));
  }
  results.addAll(ParsingUtils.values(new JacksonJsonParser(storage.bytes(),0,storage.length()),paths));
}
