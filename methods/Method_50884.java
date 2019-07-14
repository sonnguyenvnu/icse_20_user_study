private void addAndThrowLexicalError(SourceCode sourceCode) throws IOException {
  configuration.tokenizer().tokenize(sourceCode,tokens);
  listener.addedFile(1,new File(sourceCode.getFileName()));
  source.put(sourceCode.getFileName(),sourceCode);
}
