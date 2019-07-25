public Long call() throws Exception {
  try {
    return fpSetRMI.checkFPs();
  }
 catch (  IOException e) {
    MP.printError(EC.GENERAL,e);
    return Long.MAX_VALUE;
  }
}
