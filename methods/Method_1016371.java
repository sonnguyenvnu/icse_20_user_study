public boolean DEL(){
  if (this.cmd.length != 2) {
    Data.logger.warn("Syntax: DEL <file>");
    return true;
  }
  if (notConnected()) {
    return LDEL();
  }
  try {
    rmForced(this.cmd[1]);
  }
 catch (  final IOException e) {
    Data.logger.warn("Error: deletion of file " + this.cmd[1] + " failed.");
  }
  return true;
}
