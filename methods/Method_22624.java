public void setenv(String variable,String value){
  CLibrary clib=CLibrary.INSTANCE;
  clib.setenv(variable,value,1);
}
