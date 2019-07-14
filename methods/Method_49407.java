static IndexFeatures.Builder coreFeatures(){
  return new IndexFeatures.Builder().setDefaultStringMapping(Mapping.TEXT).supportedStringMappings(Mapping.TEXT,Mapping.TEXTSTRING,Mapping.STRING).setWildcardField("_all").supportsCardinality(Cardinality.SINGLE).supportsCardinality(Cardinality.LIST).supportsCardinality(Cardinality.SET).supportsNanoseconds().supportsCustomAnalyzer().supportNotQueryNormalForm();
}
