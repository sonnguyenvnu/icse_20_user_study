private void write(Element element,jetbrains.mps.project.Project project){
  Element versionXML=new Element(VERSION);
  versionXML.setAttribute(ID,VERSION_NUMBER);
  element.addContent(versionXML);
  Element tabsXML=new Element(TABS);
  for (  UsageViewData usageViewData : myUsageViewsData) {
    if (usageViewData.isTransientView()) {
      continue;
    }
    try {
      Element tabXML=new Element(TAB);
      usageViewData.write(tabXML,project);
      tabsXML.addContent(tabXML);
    }
 catch (    CantSaveSomethingException e) {
    }
  }
  element.addContent(tabsXML);
  Element defaultViewOptionsXML=new Element(DEFAULT_VIEW_OPTIONS);
  myDefaultViewOptions.write(defaultViewOptionsXML,project);
  element.addContent(defaultViewOptionsXML);
}
