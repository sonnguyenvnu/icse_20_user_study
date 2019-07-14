void addFileErr(String file,int begin,int end,String msg){
  Diagnostic d=new Diagnostic(file,Diagnostic.Category.ERROR,begin,end,msg);
  getFileErrs(file,semanticErrors).add(d);
}
