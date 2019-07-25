private void read(Element element,jetbrains.mps.project.Project project){
  Element versionXML=element.getChild(VERSION);
  if (versionXML == null) {
    return;
  }
  String version=versionXML.getAttribute(ID).getValue();
  if (!VERSION_NUMBER.equals(version)) {
    return;
  }
  Element tabsXML=element.getChild(TABS);
  if (tabsXML != null) {
    for (    Element tabXML : tabsXML.getChildren()) {
      final UsageViewData usageViewData;
      try {
        usageViewData=UsageViewData.read(this,tabXML,project);
      }
 catch (      RuntimeException ex) {
        Logger.getLogger(UsagesViewTool.class).info("Failed to restore usages view tab",ex);
        continue;
      }
catch (      CantLoadSomethingException e) {
        continue;
      }
      register(usageViewData);
      ApplicationManager.getApplication().invokeLater(() -> {
        final String caption=usageViewData.myUsagesView.getCaption();
        final Icon icon=usageViewData.myUsagesView.getIcon();
        addContent(usageViewData.myUsagesView.getComponent(),caption,icon,true);
      }
);
    }
  }
  Element defaultViewOptionsXML=element.getChild(DEFAULT_VIEW_OPTIONS);
  myDefaultViewOptions.read(defaultViewOptionsXML,project);
  ApplicationManager.getApplication().invokeLater(() -> {
    ContentManager cm=getContentManager();
    if (cm == null) {
      return;
    }
    if (cm.getContentCount() == 0) {
      makeUnavailableLater();
    }
  }
);
}
