public boolean LRMDIR(){
  if (this.cmd.length != 2) {
    Data.logger.warn("Syntax: LRMDIR <folder-name>");
    return true;
  }
  final File f=new File(this.currentLocalPath,this.cmd[1]);
  if (!f.exists()) {
    Data.logger.warn("Error: local folder " + this.cmd[1] + " does not exist");
  }
 else {
    if (!f.delete()) {
      Data.logger.warn("Error: deletion of local folder " + this.cmd[1] + " failed");
    }
  }
  return true;
}
