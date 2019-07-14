@SuppressWarnings("unchecked") protected DefaultMutableTreeNode searchTreeNode(URI uri,DefaultMutableTreeNode node){
  if (node instanceof TreeNodeExpandable) {
    ((TreeNodeExpandable)node).populateTreeNode(api);
  }
  String u=uri.toString();
  T child=null;
  Enumeration enumeration=node.children();
  while (enumeration.hasMoreElements()) {
    T element=(T)enumeration.nextElement();
    String childU=element.getUri().toString();
    if (u.length() > childU.length()) {
      if (u.startsWith(childU)) {
        char c=u.charAt(childU.length());
        if ((c == '/') || (c == '!')) {
          child=element;
          break;
        }
      }
    }
 else     if (u.equals(childU)) {
      child=element;
      break;
    }
  }
  if (child != null) {
    if (u.equals(child.getUri().toString())) {
      return child;
    }
 else {
      return searchTreeNode(uri,child);
    }
  }
 else {
    return null;
  }
}
