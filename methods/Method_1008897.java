/** 
 * Preprocess content controls which have tag "od:condition|od:repeat|od:component". It is "preprocess" in the sense that it is "pre" opening in Word The algorithm is as follows: Inject components first. Look at each top level SDT (ShallowTraversor). If it does not have a real data binding, it might have a bindingrole tag we need to process (processBindingRoleIfAny). Conditionals are easy. processRepeat method: - clones the sdt n times - invokes DeepTraversor which changes xpath binding on descendant sdts (both sdts with real bindings and sdts with bindingrole tags). It is not the job of DeepTraversor to expand out any other repeats it might encounter, or to resolve conditionals. Those things are done by ShallowTraversor, to which control returns, as it continues its traverse. The implementation of 13 Sept 2010 replaced the previous XPath based implementation, which did not support nested repeats. I've chosen to build this around TraversalUtil, instead of using XSLT, and this seems to have worked out nicely. The implementation of 10 October 2010 replaced the v1 conventions implementation with a v2 implementation. The main method in this class can convert v1 documents to v2. The v2 implementation is not yet complete. All v1 features are implemented, but not the new v2 stuff (eg complex conditions).
 * @param documentPart
 * @throws Exception
 */
public WordprocessingMLPackage preprocess() throws Docx4JException {
  Set<ContentAccessor> partList=getParts(wordMLPackage);
  try {
    for (    ContentAccessor part : partList) {
      new TraversalUtil(part,shallowTraversor);
    }
  }
 catch (  InputIntegrityException iie) {
    throw new Docx4JException(iie.getMessage(),iie);
  }
  if (wordMLPackage.getMainDocumentPart().getXPathsPart() == null) {
    log.info("No XPaths part; ok if you are just processing w15 repeatingSection");
  }
 else {
    wordMLPackage.getMainDocumentPart().getXPathsPart().getContents().getXpath().clear();
    wordMLPackage.getMainDocumentPart().getXPathsPart().getContents().getXpath().addAll(xpathsMap.values());
  }
  if (wordMLPackage.getMainDocumentPart().getConditionsPart() != null) {
    wordMLPackage.getMainDocumentPart().getConditionsPart().getContents().getCondition().clear();
    wordMLPackage.getMainDocumentPart().getConditionsPart().getContents().getCondition().addAll(conditionsMap.values());
  }
  log.debug(this.conditionTiming.toString());
  log.debug("conditions in total: " + this.conditionTimingTotal / 1000);
  return wordMLPackage;
}
