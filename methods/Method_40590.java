public void putProblem(@Nullable String file,int begin,int end,String msg){
  if (file != null) {
    addFileErr(file,begin,end,msg);
  }
}
