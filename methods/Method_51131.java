private void phase2(boolean flag){
  int i=0;
  while (!currentPath.isEndNode() && i < this.maxLoops) {
    i++;
    if (currentPath.isBranch() || currentPath.isFirstDoStatement()) {
      if (flag) {
        addNodeToTree();
      }
      flag=true;
      if (countLoops() <= 2) {
        addCurrentChild();
        continue;
      }
 else {
        addLastChild();
        continue;
      }
    }
 else {
      addCurrentChild();
    }
  }
}
