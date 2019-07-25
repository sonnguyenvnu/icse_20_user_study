public boolean LPWD(){
  if (this.cmd.length != 1) {
    Data.logger.warn("Syntax: LPWD  (no parameter)");
    return true;
  }
  Data.logger.info("---- Local path: " + this.currentLocalPath.toString());
  return true;
}
