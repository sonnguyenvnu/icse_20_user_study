public void putProblem(Node loc,String msg){
  String file;
  if (loc != null && ((file=loc.getFile()) != null)) {
    addFileErr(file,loc.start(),loc.end(),msg);
  }
}
