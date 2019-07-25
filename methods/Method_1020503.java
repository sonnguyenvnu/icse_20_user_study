public void info(String detailMessage,Object... args){
  problemsBySeverity.put(Severity.INFO,String.format(detailMessage,args));
}
