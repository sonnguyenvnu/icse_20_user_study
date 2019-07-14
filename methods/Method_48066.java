@Override public Map<String,String> getCompressionOptions(String cf) throws BackendException {
  CFMetaData cfm=Schema.instance.getCFMetaData(keySpaceName,cf);
  if (cfm == null)   return null;
  return ImmutableMap.copyOf(cfm.compressionParameters().asThriftOptions());
}
