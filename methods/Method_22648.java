public void setenv(String variable,String value){
  getLibC()._putenv(variable + "=" + value);
}
