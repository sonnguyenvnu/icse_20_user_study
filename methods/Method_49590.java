private static String analyzerNameFor(final Parameter[] parameters,final Mapping mapping,final String defaultStringAnalyzer,final String defaultTextAnalyzer){
switch (mapping) {
case TEXTSTRING:
    throw new RuntimeException("TextString is an unsupported mapping for string data & custom analyzers");
case PREFIX_TREE:
  throw new RuntimeException("Prefix-tree is an unsupported mapping for string data & custom analyzers");
case STRING:
return ParameterType.STRING_ANALYZER.findParameter(parameters,defaultStringAnalyzer);
case TEXT:
case DEFAULT:
return ParameterType.TEXT_ANALYZER.findParameter(parameters,defaultTextAnalyzer);
default :
throw new RuntimeException("Not supported");
}
}
