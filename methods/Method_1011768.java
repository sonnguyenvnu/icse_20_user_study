protected DiffModelTree.TreeNode rebuild(){
  DiffModelTree.ModelTreeNode modelNode=new DiffModelTree.ModelTreeNode();
  myRootNodes=Sequence.fromIterable(getAffectedRoots()).where(new IWhereFilter<SNodeId>(){
    public boolean accept(    SNodeId r){
      return r != null;
    }
  }
).select(new ISelector<SNodeId,DiffModelTree.RootTreeNode>(){
    public DiffModelTree.RootTreeNode select(    SNodeId r){
      return new DiffModelTree.RootTreeNode(r);
    }
  }
).sort(new ISelector<DiffModelTree.RootTreeNode,String>(){
    public String select(    DiffModelTree.RootTreeNode rtn){
      return rtn.myVirtualPackage + "|" + rtn.myPresentation;
    }
  }
,true).toListSequence();
  for (  DiffModelTree.RootTreeNode rtn : ListSequence.fromList(myRootNodes)) {
    DiffModelTree.TreeNode parentNode=modelNode;
    if (isNotEmptyString(rtn.myVirtualPackage)) {
      for (      final String sub : Sequence.fromArray(rtn.myVirtualPackage.split("\\."))) {
        Iterable<DiffModelTree.TreeNode> children=Collections.list(((Enumeration)parentNode.children()));
        DiffModelTree.TreeNode child=Sequence.fromIterable(children).findFirst(new IWhereFilter<DiffModelTree.TreeNode>(){
          public boolean accept(          DiffModelTree.TreeNode c){
            return c instanceof DiffModelTree.PackageTreeNode && sub.equals(c.getText());
          }
        }
);
        if (child == null) {
          child=new DiffModelTree.PackageTreeNode(sub);
          parentNode.add(child);
        }
        parentNode=child;
      }
    }
    parentNode.add(rtn);
  }
  if (Sequence.fromIterable(getAffectedRoots()).any(new IWhereFilter<SNodeId>(){
    public boolean accept(    SNodeId r){
      return r == null;
    }
  }
)) {
    DiffModelTree.RootTreeNode metadataNode=new DiffModelTree.MetadataTreeNode();
    ListSequence.fromList(myRootNodes).addElement(metadataNode);
    modelNode.add(metadataNode);
  }
  return modelNode;
}
