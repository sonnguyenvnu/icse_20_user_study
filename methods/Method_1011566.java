public static ProcessHandler redirect(ProcessHandler handler,ProcessListener listener){
  handler.addProcessListener(listener);
  return handler;
}
