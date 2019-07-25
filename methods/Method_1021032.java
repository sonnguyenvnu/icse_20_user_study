protected void working(){
  if (!rootDir.contains(";")) {
    scan(new File(rootDir));
  }
 else {
    String[] paths=rootDir.split(";");
    for (    String path : paths) {
      scan(new File(path));
    }
  }
  compare();
  preScan.clear();
  preScan.putAll(curScan);
  curScan.clear();
}
