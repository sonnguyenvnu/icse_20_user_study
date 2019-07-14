public int unsetenv(String variable){
  CLibrary clib=CLibrary.INSTANCE;
  return clib.unsetenv(variable);
}
