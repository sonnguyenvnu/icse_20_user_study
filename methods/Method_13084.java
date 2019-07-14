@SuppressWarnings("unchecked") protected void treeNodeChanged(T node){
  if (treeNodeChangedEnabled && (node != null)) {
    try {
      updateTreeMenuEnabled=false;
      URI uri=node.getUri();
      if ((uri.getFragment() == null) && (uri.getQuery() == null)) {
        showPage(uri,uri,node);
      }
 else {
        URI baseUri=new URI(uri.getScheme(),uri.getHost(),uri.getPath(),null);
        T baseNode=node;
        while ((baseNode != null) && !baseNode.getUri().equals(baseUri)) {
          baseNode=(T)baseNode.getParent();
        }
        if ((baseNode != null) && baseNode.getUri().equals(baseUri)) {
          showPage(uri,baseUri,baseNode);
        }
      }
    }
 catch (    URISyntaxException e) {
      assert ExceptionUtil.printStackTrace(e);
    }
 finally {
      updateTreeMenuEnabled=true;
    }
  }
}
