public static void executeInThread(Runnable runnable){
  new Thread(runnable).start();
}
