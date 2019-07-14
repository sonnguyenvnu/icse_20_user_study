@SuppressWarnings("unchecked") private List<String> customTokenize(String tokenizerClass,String value){
  CachingTokenFilter stream=null;
  try {
    final List<String> terms=new ArrayList<>();
    final Tokenizer tokenizer=((Constructor<Tokenizer>)ClassLoader.getSystemClassLoader().loadClass(tokenizerClass).getConstructor()).newInstance();
    tokenizer.setReader(new StringReader(value));
    stream=new CachingTokenFilter(tokenizer);
    final TermToBytesRefAttribute termAtt=stream.getAttribute(TermToBytesRefAttribute.class);
    stream.reset();
    while (stream.incrementToken()) {
      terms.add(termAtt.getBytesRef().utf8ToString());
    }
    return terms;
  }
 catch (  ReflectiveOperationException|IOException e) {
    throw new IllegalArgumentException(e.getMessage(),e);
  }
 finally {
    IOUtils.closeQuietly(stream);
  }
}
