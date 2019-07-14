void extractClassToTextPane(TypeReference type,String tabTitle,String path,String navigatonLink) throws Exception {
  if (tabTitle == null || tabTitle.trim().length() < 1 || path == null) {
    throw new FileEntryNotFoundException();
  }
  OpenFile sameTitledOpen=null;
  for (  OpenFile nextOpen : hmap) {
    if (tabTitle.equals(nextOpen.name)) {
      sameTitledOpen=nextOpen;
      break;
    }
  }
  if (sameTitledOpen != null && path.equals(sameTitledOpen.path) && type.equals(sameTitledOpen.getType()) && sameTitledOpen.isContentValid()) {
    sameTitledOpen.setInitialNavigationLink(navigatonLink);
    addOrSwitchToTab(sameTitledOpen);
    return;
  }
  TypeDefinition resolvedType=null;
  if (type == null || ((resolvedType=type.resolve()) == null)) {
    throw new Exception("Unable to resolve type.");
  }
  if (sameTitledOpen != null) {
    sameTitledOpen.path=path;
    sameTitledOpen.invalidateContent();
    sameTitledOpen.setDecompilerReferences(metadataSystem,settings,decompilationOptions);
    sameTitledOpen.setType(resolvedType);
    sameTitledOpen.setInitialNavigationLink(navigatonLink);
    sameTitledOpen.resetScrollPosition();
    sameTitledOpen.decompile();
    addOrSwitchToTab(sameTitledOpen);
  }
 else {
    OpenFile open=new OpenFile(tabTitle,path,getTheme(),mainWindow);
    open.setDecompilerReferences(metadataSystem,settings,decompilationOptions);
    open.setType(resolvedType);
    open.setInitialNavigationLink(navigatonLink);
    open.decompile();
    hmap.add(open);
    addOrSwitchToTab(open);
  }
}
