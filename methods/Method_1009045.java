public boolean evaluate(WordprocessingMLPackage pkg,Map<String,CustomXmlPart> customXmlDataStorageParts,Map<String,Condition> conditionsMap,Map<String,org.opendope.xpaths.Xpaths.Xpath> xpathsMap){
  org.opendope.xpaths.Xpaths.Xpath xpath=xpathsMap.get(id);
  String val=BindingHandler.xpathGetString(pkg,customXmlDataStorageParts,xpath.getDataBinding().getStoreItemID(),xpath.getDataBinding().getXpath(),xpath.getDataBinding().getPrefixMappings());
  return Boolean.parseBoolean(val);
}
