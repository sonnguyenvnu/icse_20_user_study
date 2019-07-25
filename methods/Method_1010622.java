public void log(Throwable e){
  StringBuffer sb=WorkerBase.extractStackTrace(e);
  error(sb.toString());
}
