public String getenv(String variable){
  CLibrary clib=CLibrary.INSTANCE;
  return clib.getenv(variable);
}
