/** 
 * Commits source code to the model. all existing source will be replaced.
 */
public void commitSource(String source,LanguageVersion languageVersion){
  LanguageVersionHandler languageVersionHandler=languageVersion.getLanguageVersionHandler();
  Node node=languageVersionHandler.getParser(languageVersionHandler.getDefaultParserOptions()).parse(null,new StringReader(source));
  rootNode=node;
  fireViewerModelEvent(new ViewerModelEvent(this,ViewerModelEvent.CODE_RECOMPILED));
}
