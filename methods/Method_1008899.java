public boolean revert() throws Docx4JException {
  TopLevelSdtTemplateFinder sdtPrFinder=new TopLevelSdtTemplateFinder(false);
  findSdtsInTemplate(openDopePkg,sdtPrFinder);
  templateConditionSdtsByID=sdtPrFinder.conditionSdtsByID;
  templateRepeatSdtsByID=sdtPrFinder.repeatSdtsByID;
  handleSdtsInInstance();
  return sanityCheck();
}
