public void putProblem(@NotNull Node loc,String msg){
  String file=loc.file;
  if (file != null) {
    addFileErr(file,loc.start,loc.end,msg);
  }
}
