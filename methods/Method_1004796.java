public static ScrollReaderConfigBuilder builder(ValueReader reader,Mapping resolvedMapping,Settings settings){
  return builder(reader,settings).setResolvedMapping(resolvedMapping);
}
