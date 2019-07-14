private void addNodeToTree(){
  if (currentPath.isFirstDoStatement()) {
    DefaultMutableTreeNode level=stack;
    DataFlowNode doBranch=currentPath.getDoBranchNodeFromFirstDoStatement();
    while (true) {
      if (level.getChildCount() != 0) {
        PathElement ref=this.isNodeInLevel(level);
        if (ref != null) {
          this.addRefPseudoPathElement(level,ref);
          break;
        }
 else {
          level=this.getLastChildNode(level);
          continue;
        }
      }
 else {
        this.addNewPseudoPathElement(level,doBranch);
        break;
      }
    }
  }
  if (currentPath.isBranch()) {
    DefaultMutableTreeNode level=stack;
    if (currentPath.isDoBranchNode()) {
      while (!this.equalsPseudoPathElementWithDoBranchNodeInLevel(level)) {
        level=this.getLastChildNode(level);
        if (level.getChildCount() == 0) {
          break;
        }
      }
      PathElement ref=this.getDoBranchNodeInLevel(level);
      if (ref != null) {
        addNode(level,ref);
      }
 else {
        this.addNewPathElement(level);
      }
    }
 else {
      while (true) {
        if (level.getChildCount() != 0) {
          PathElement ref=this.isNodeInLevel(level);
          if (ref != null) {
            addNode(level,ref);
            break;
          }
 else {
            level=this.getLastChildNode(level);
            continue;
          }
        }
 else {
          this.addNewPathElement(level);
          break;
        }
      }
    }
  }
}
