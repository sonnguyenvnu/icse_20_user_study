@Override protected MPSTreeNode rebuild(){
  MPSTreeNode root=new TextTreeNode("no bookmarks");
  root.setIcon(IdeIcons.DEFAULT_ICON);
  List<SNodeReference> nodePointers=myBookmarkManager.getAllNumberedBookmarks();
  boolean hasBookmarks=false;
  for (int i=0; i < nodePointers.size(); i++) {
    final SNodeReference nodePointer=nodePointers.get(i);
    if (nodePointer != null && nodePointer.resolve(myProject.getRepository()) != null) {
      hasBookmarks=true;
      TextTreeNode textTreeNode=new MyTextTreeNodeNumbered(i);
      textTreeNode.setIcon(BookmarkManager.getIcon(i));
      textTreeNode.add(new SNodeTreeNode(nodePointer.resolve(myProject.getRepository())));
      root.add(textTreeNode);
    }
  }
  nodePointers=myBookmarkManager.getAllUnnumberedBookmarks();
  for (  SNodeReference nodePointer : nodePointers) {
    if (nodePointer != null && nodePointer.resolve(myProject.getRepository()) != null) {
      hasBookmarks=true;
      TextTreeNode textTreeNode=new MyTextTreeNodeUnnumbered(nodePointer);
      textTreeNode.setIcon(BookmarkManager.getIcon(-1));
      textTreeNode.add(new SNodeTreeNode(nodePointer.resolve(myProject.getRepository())));
      root.add(textTreeNode);
    }
  }
  if (hasBookmarks) {
    root.setText("bookmarks");
  }
  return root;
}
