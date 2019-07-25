public boolean LDIR(){
  if (this.cmd.length != 1) {
    Data.logger.warn("Syntax: LDIR  (no parameter)");
    return true;
  }
  final String[] name=this.currentLocalPath.list();
  for (  String element : name) {
    Data.logger.info(ls(new File(this.currentLocalPath,element)));
  }
  return true;
}
