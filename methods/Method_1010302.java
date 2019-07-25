@NotNull public Dependency copy(){
  Dependency result=new Dependency();
  result.myModuleRef=myModuleRef;
  result.myReexport=myReexport;
  result.myScope=myScope;
  return result;
}
