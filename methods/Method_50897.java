private Language createLanguage(){
  Properties p=new Properties();
  if (ignoreLiterals) {
    p.setProperty(Tokenizer.IGNORE_LITERALS,"true");
  }
  if (ignoreIdentifiers) {
    p.setProperty(Tokenizer.IGNORE_IDENTIFIERS,"true");
  }
  if (ignoreAnnotations) {
    p.setProperty(Tokenizer.IGNORE_ANNOTATIONS,"true");
  }
  if (ignoreUsings) {
    p.setProperty(Tokenizer.IGNORE_USINGS,"true");
  }
  p.setProperty(Tokenizer.OPTION_SKIP_BLOCKS,Boolean.toString(skipBlocks));
  p.setProperty(Tokenizer.OPTION_SKIP_BLOCKS_PATTERN,skipBlocksPattern);
  return LanguageFactory.createLanguage(language,p);
}
