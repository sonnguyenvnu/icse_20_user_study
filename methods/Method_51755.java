@Override public void tokenize(SourceCode sourceCode,Tokens tokenEntries) throws IOException {
  constructorDetector=new ConstructorDetector(ignoreIdentifiers);
  super.tokenize(sourceCode,tokenEntries);
}
