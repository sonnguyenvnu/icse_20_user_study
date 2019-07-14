void addFileErr(String file,int beg,int end,String msg){
  List<Diagnostic> msgs=getFileErrs(file,problems);
  msgs.add(new Diagnostic(file,Diagnostic.Type.ERROR,beg,end,msg));
}
