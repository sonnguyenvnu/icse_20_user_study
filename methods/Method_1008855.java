public static void process(WordprocessingMLPackage wmlPackage){
  ListsToContentControls lc=new ListsToContentControls(wmlPackage);
  if (lc.ndp == null) {
    log.info("No NumberingDefinitionsPart, skipping");
    return;
  }
  lc.process();
}
