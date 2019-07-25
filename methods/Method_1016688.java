public boolean RMDIR(){
  if (this.cmd.length != 2) {
    Data.logger.warn("Syntax: RMDIR <folder-name>");
    return true;
  }
  if (notConnected()) {
    return LRMDIR();
  }
  try {
    rmForced(this.cmd[1]);
  }
 catch (  final IOException e) {
    Data.logger.warn("Error: deletion of folder " + this.cmd[1] + " failed.");
  }
  return true;
}
