private void processSource(Reader sourceCode,RuleSets ruleSets,RuleContext ctx){
  LanguageVersion languageVersion=ctx.getLanguageVersion();
  LanguageVersionHandler languageVersionHandler=languageVersion.getLanguageVersionHandler();
  Parser parser=PMD.parserFor(languageVersion,configuration);
  Node rootNode=parse(ctx,sourceCode,parser);
  resolveQualifiedNames(rootNode,languageVersionHandler);
  symbolFacade(rootNode,languageVersionHandler);
  Language language=languageVersion.getLanguage();
  usesDFA(languageVersion,rootNode,ruleSets,language);
  usesTypeResolution(languageVersion,rootNode,ruleSets,language);
  usesMultifile(rootNode,languageVersionHandler,ruleSets,language);
  List<Node> acus=Collections.singletonList(rootNode);
  ruleSets.apply(acus,ctx,language);
}
