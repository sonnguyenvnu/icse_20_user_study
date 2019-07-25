@Override public void convert(boolean askPath,Consumer<String>... nextStep){
  try {
    htmlBookPath=directoryService.getSaveOutputPath(ExtensionFilters.HTML,askPath);
    indikatorService.startProgressBar();
    logger.debug("HTML conversion started");
    final String asciidoc=current.currentEditorValue();
    String rendered=converterProvider.get(htmlConfigBean).convertHtml(asciidoc).getRendered();
    IOHelper.writeToFile(htmlBookPath,rendered,CREATE,TRUNCATE_EXISTING);
    controller.addRemoveRecentList(htmlBookPath);
    indikatorService.stopProgressBar();
    logger.debug("HTML conversion ended");
  }
 catch (  Exception e) {
    logger.error("Problem occured while converting to HTML",e);
  }
 finally {
    indikatorService.stopProgressBar();
  }
}
