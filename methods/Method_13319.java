@Override protected void openHyperlink(int x,int y,HyperlinkData hyperlinkData){
  HyperlinkReferenceData hyperlinkReferenceData=(HyperlinkReferenceData)hyperlinkData;
  if (hyperlinkReferenceData.reference.enabled) {
    try {
      Point location=textArea.getLocationOnScreen();
      int offset=textArea.viewToModel(new Point(x - location.x,y - location.y));
      URI uri=entry.getUri();
      api.addURI(new URI(uri.getScheme(),uri.getAuthority(),uri.getPath(),"position=" + offset,null));
      ModuleInfoReferenceData moduleInfoReferenceData=(ModuleInfoReferenceData)hyperlinkReferenceData.reference;
      List<Container.Entry> entries;
      String fragment;
switch (moduleInfoReferenceData.type) {
case TYPE:
        entries=IndexesUtil.findInternalTypeName(collectionOfFutureIndexes,fragment=moduleInfoReferenceData.typeName);
      break;
case PACKAGE:
    entries=IndexesUtil.find(collectionOfFutureIndexes,"packageDeclarations",moduleInfoReferenceData.typeName);
  fragment=null;
break;
default :
entries=IndexesUtil.find(collectionOfFutureIndexes,"javaModuleDeclarations",moduleInfoReferenceData.name);
fragment=moduleInfoReferenceData.typeName;
break;
}
if (entries.contains(entry)) {
api.openURI(uri);
}
 else {
String rootUri=entry.getContainer().getRoot().getUri().toString();
ArrayList<Container.Entry> sameContainerEntries=new ArrayList<>();
for (Container.Entry entry : entries) {
if (entry.getUri().toString().startsWith(rootUri)) {
sameContainerEntries.add(entry);
}
}
if (sameContainerEntries.size() > 0) {
api.openURI(x,y,sameContainerEntries,null,fragment);
}
 else if (entries.size() > 0) {
api.openURI(x,y,entries,null,fragment);
}
}
}
 catch (URISyntaxException e) {
assert ExceptionUtil.printStackTrace(e);
}
}
}
