public long size(){
  try {
    return this.fpSet.size();
  }
 catch (  IOException e) {
    MP.printError(EC.GENERAL,e);
    return -1;
  }
}
