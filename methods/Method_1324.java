private static String getMsg(String msg,Throwable tr){
  return msg + '\n' + getStackTraceString(tr);
}
