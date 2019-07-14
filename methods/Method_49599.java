private List<String> customTokenize(Analyzer analyzer,String fieldName,String value){
  final List<String> terms=new ArrayList<>();
  try (CachingTokenFilter stream=new CachingTokenFilter(analyzer.tokenStream(fieldName,value))){
    final TermToBytesRefAttribute termAtt=stream.getAttribute(TermToBytesRefAttribute.class);
    stream.reset();
    while (stream.incrementToken()) {
      terms.add(termAtt.getBytesRef().utf8ToString());
    }
    return terms;
  }
 catch (  IOException e) {
    throw new IllegalArgumentException(e.getMessage(),e);
  }
}
