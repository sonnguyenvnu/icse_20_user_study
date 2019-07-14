static Node getCompilationUnit(LanguageVersionHandler languageVersionHandler,String code){
  Parser parser=languageVersionHandler.getParser(languageVersionHandler.getDefaultParserOptions());
  Node node=parser.parse(null,new StringReader(code));
  languageVersionHandler.getSymbolFacade().start(node);
  languageVersionHandler.getTypeResolutionFacade(Designer.class.getClassLoader()).start(node);
  return node;
}
