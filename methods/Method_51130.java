private void phase1(){
  currentPath.addLast(rootNode);
  int i=0;
  boolean flag=true;
  do {
    i++;
    phase2(flag);
    shim.execute(currentPath);
    flag=false;
  }
 while (i < maxPaths && phase3());
}
