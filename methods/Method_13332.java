@Override @SuppressWarnings("unchecked") protected void openHyperlink(int x,int y,HyperlinkData hyperlinkData){
  HyperlinkReferenceData hyperlinkReferenceData=(HyperlinkReferenceData)hyperlinkData;
  if (hyperlinkReferenceData.reference.enabled) {
    try {
      Point location=textArea.getLocationOnScreen();
      int offset=textArea.viewToModel(new Point(x - location.x,y - location.y));
      URI uri=entry.getUri();
      api.addURI(new URI(uri.getScheme(),uri.getAuthority(),uri.getPath(),"position=" + offset,null));
      ReferenceData reference=hyperlinkReferenceData.reference;
      String typeName=reference.typeName;
      List<Container.Entry> entries=IndexesUtil.findInternalTypeName(collectionOfFutureIndexes,typeName);
      String fragment=typeName;
      if (reference.name != null) {
        fragment+='-' + reference.name;
      }
      if (reference.descriptor != null) {
        fragment+='-' + reference.descriptor;
      }
      if (entries.contains(entry)) {
        api.openURI(new URI(uri.getScheme(),uri.getAuthority(),uri.getPath(),fragment));
      }
 else {
        String rootUri=entry.getContainer().getRoot().getUri().toString();
        ArrayList<Container.Entry> sameContainerEntries=new ArrayList<>();
        for (        Container.Entry entry : entries) {
          if (entry.getUri().toString().startsWith(rootUri)) {
            sameContainerEntries.add(entry);
          }
        }
        if (sameContainerEntries.size() > 0) {
          api.openURI(x,y,sameContainerEntries,null,fragment);
        }
 else         if (entries.size() > 0) {
          api.openURI(x,y,entries,null,fragment);
        }
      }
    }
 catch (    URISyntaxException e) {
      assert ExceptionUtil.printStackTrace(e);
    }
  }
}
