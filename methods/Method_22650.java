public int unsetenv(String variable){
  return getLibC()._putenv(variable + "=");
}
