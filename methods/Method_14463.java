static public Cell loadStreaming(String s,Pool pool) throws Exception {
  InjectableValues injectableValues=new InjectableValues.Std().addValue("pool",pool);
  return ParsingUtilities.mapper.setInjectableValues(injectableValues).readValue(s,Cell.class);
}
