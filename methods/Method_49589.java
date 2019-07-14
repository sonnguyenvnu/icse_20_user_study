@Override final protected Analyzer getWrappedAnalyzer(String fieldName){
  final KeyInformation keyInformation=informations.get(store,fieldName);
  if (keyInformation == null || !String.class.isAssignableFrom(keyInformation.getDataType())) {
    return analyzerFor(STANDARD_ANALYZER);
  }
  final Parameter[] parameters=keyInformation.getParameters();
  final Mapping mapping=ParameterType.MAPPING.findParameter(parameters,Mapping.DEFAULT);
  return analyzerFor(analyzerNameFor(parameters,mapping,KEYWORD_ANALYZER,STANDARD_ANALYZER));
}
