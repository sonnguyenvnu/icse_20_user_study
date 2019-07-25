public boolean LCD(){
  if (this.cmd.length != 2) {
    Data.logger.warn("Syntax: LCD <path>");
    return true;
  }
  final String path=this.cmd[1];
  final File dir=new File(path);
  File newPath=dir.isAbsolute() ? dir : new File(this.currentLocalPath,path);
  try {
    newPath=new File(newPath.getCanonicalPath());
  }
 catch (  final IOException e) {
  }
  if (newPath.exists()) {
    if (newPath.isDirectory()) {
      this.currentLocalPath=newPath;
      Data.logger.info("---- New local path: " + this.currentLocalPath.toString());
    }
 else {
      Data.logger.warn("Error: local path " + newPath.toString() + " denotes not a directory.");
    }
  }
 else {
    Data.logger.warn("Error: local path " + newPath.toString() + " does not exist.");
  }
  return true;
}
