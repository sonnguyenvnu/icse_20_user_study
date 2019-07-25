public boolean PWD(){
  if (this.cmd.length > 1) {
    Data.logger.warn("Syntax: PWD  (no parameter)");
    return true;
  }
  if (notConnected()) {
    return LPWD();
  }
  try {
    Data.logger.info("---- Current remote path is: " + pwd());
  }
 catch (  final IOException e) {
    Data.logger.warn("Error: remote path not available");
  }
  return true;
}
