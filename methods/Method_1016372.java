public boolean GET(){
  if ((this.cmd.length < 2) || (this.cmd.length > 3)) {
    Data.logger.warn("Syntax: GET <remote-file> [<local-file>]");
    return true;
  }
  final String remote=this.cmd[1];
  final boolean withoutLocalFile=this.cmd.length == 2;
  final String localFilename=(withoutLocalFile) ? remote : this.cmd[2];
  final File local=absoluteLocalFile(localFilename);
  if (local.exists()) {
    Data.logger.warn("Error: local file " + local.toString() + " already exists.\n" + "               File " + remote + " not retrieved. Local file unchanged.");
  }
 else {
    if (withoutLocalFile) {
      retrieveFilesRecursively(remote,false);
    }
 else {
      try {
        get(local.getAbsolutePath(),remote);
      }
 catch (      final IOException e) {
        Data.logger.warn("Error: retrieving file " + remote + " failed. (" + e.getMessage() + ")");
      }
    }
  }
  return true;
}
