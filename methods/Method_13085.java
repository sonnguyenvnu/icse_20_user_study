@SuppressWarnings("unchecked") protected <P extends JComponent & UriGettable>boolean showPage(URI uri,URI baseUri,DefaultMutableTreeNode baseNode){
  P page=(P)tabbedPanel.showPage(baseUri);
  if ((page == null) && (baseNode instanceof PageCreator)) {
    page=((PageCreator)baseNode).createPage(api);
    page.putClientProperty("node",baseNode);
    String path=baseUri.getPath();
    String label=path.substring(path.lastIndexOf('/') + 1);
    Object data=baseNode.getUserObject();
    if (data instanceof TreeNodeData) {
      TreeNodeData tnd=(TreeNodeData)data;
      tabbedPanel.addPage(label,tnd.getIcon(),tnd.getTip(),page);
    }
 else {
      tabbedPanel.addPage(label,null,null,page);
    }
  }
  if (openUriEnabled && page instanceof UriOpenable) {
    ((UriOpenable)page).openUri(uri);
  }
  return (page != null);
}
