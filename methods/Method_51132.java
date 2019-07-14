private boolean phase3(){
  while (!currentPath.isEmpty()) {
    if (currentPath.isBranch()) {
      if (this.countLoops() == 1) {
        if (this.hasMoreChildren()) {
          this.incChild();
          return true;
        }
 else {
          this.removeFromTree();
          currentPath.removeLast();
        }
      }
 else {
        this.removeFromTree();
        currentPath.removeLast();
      }
    }
 else {
      currentPath.removeLast();
    }
  }
  return false;
}
