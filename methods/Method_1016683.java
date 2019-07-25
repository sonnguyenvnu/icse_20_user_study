public boolean LRM(){
  if (this.cmd.length != 2) {
    Data.logger.warn("Syntax: LRM <file-name>");
    return true;
  }
  final File f=new File(this.currentLocalPath,this.cmd[1]);
  if (!f.exists()) {
    Data.logger.warn("Error: local file " + this.cmd[1] + " does not exist");
  }
 else {
    if (!f.delete()) {
      Data.logger.warn("Error: deletion of file " + this.cmd[1] + " failed");
    }
  }
  return true;
}
