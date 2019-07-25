public EditorCell find(){
  SRepository repo=myCurrent.getContext().getRepository();
  return new ModelAccessHelper(repo).runReadAction(() -> {
    if (myCheckFirst && isMultipleCollectionCell(myCurrent)) {
      return myCurrent;
    }
    SNode anchorNode=getLCA(myAnchor.getSNode(),myCurrent.getSNode());
    if (anchorNode == null) {
      return null;
    }
    if (anchorNode != myCurrent.getSNode()) {
      EditorCell curr=myCurrent;
      while (curr != null && curr.getSNode() != anchorNode) {
        if (isMultipleCollectionCell(curr)) {
          return curr;
        }
        curr=curr.getParent();
      }
      if (curr != null && curr.getSNode() == anchorNode && isMultipleCollectionCell(curr)) {
        return curr;
      }
    }
    for (    EditorCell current : CellTraversalUtil.iterateTree(null,myCurrent,myForward).skipStart()) {
      SNode currentNode=current.getSNode();
      if (!jetbrains.mps.util.SNodeOperations.isAncestor(anchorNode,currentNode)) {
        return null;
      }
      if (isMultipleCollectionCell(current)) {
        return current;
      }
    }
    return null;
  }
);
}
