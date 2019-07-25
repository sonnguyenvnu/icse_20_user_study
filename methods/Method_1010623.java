public void log(String text,Throwable e){
  StringBuffer sb=WorkerBase.extractStackTrace(e);
  error(text + "\n" + sb.toString());
}
