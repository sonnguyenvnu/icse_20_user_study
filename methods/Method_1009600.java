public void received(final ActionInvocation actionInvocation,DIDLContent didl){
  log.fine("Received browse action DIDL descriptor, creating tree nodes");
  final List<DefaultMutableTreeNode> childNodes=new ArrayList<>();
  try {
    for (    Container childContainer : didl.getContainers()) {
      DefaultMutableTreeNode childNode=new DefaultMutableTreeNode(childContainer){
        @Override public boolean isLeaf(){
          return false;
        }
      }
;
      childNodes.add(childNode);
    }
    for (    Item childItem : didl.getItems()) {
      DefaultMutableTreeNode childNode=new DefaultMutableTreeNode(childItem){
        @Override public boolean isLeaf(){
          return true;
        }
      }
;
      childNodes.add(childNode);
    }
  }
 catch (  Exception ex) {
    log.fine("Creating DIDL tree nodes failed: " + ex);
    actionInvocation.setFailure(new ActionException(ErrorCode.ACTION_FAILED,"Can't create tree child nodes: " + ex,ex));
    failure(actionInvocation,null);
  }
  SwingUtilities.invokeLater(new Runnable(){
    public void run(){
      updateTreeModel(childNodes);
    }
  }
);
}
