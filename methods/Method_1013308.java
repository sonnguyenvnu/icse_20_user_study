public boolean contains(long fp){
  try {
    return this.fpSet.contains(fp);
  }
 catch (  IOException e) {
    MP.printError(EC.GENERAL,e);
    return false;
  }
}
