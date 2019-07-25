public boolean JJDECODE(){
  if (this.cmd.length != 2) {
    Data.logger.warn("Syntax: JJENCODE <path>");
    return true;
  }
  final String path=this.cmd[1];
  final File dir=new File(path);
  final File newPath=dir.isAbsolute() ? dir : new File(this.currentLocalPath,path);
  final File newFolder=new File(newPath.toString() + ".dir");
  if (newPath.exists()) {
    if (!newPath.isDirectory()) {
      if (!newFolder.mkdir()) {
        exec("mkdir \"" + path + ".dir\"",true);
      }
 else {
        Data.logger.warn("Error: target dir " + newFolder.toString() + " cannot be created");
      }
    }
 else {
      Data.logger.warn("Error: local path " + newPath.toString() + " must denote to jar/jar file");
    }
  }
 else {
    Data.logger.warn("Error: local path " + newPath.toString() + " does not exist.");
  }
  return true;
}
