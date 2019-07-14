public static synchronized ThreadManager getInstance(){
  if (threadManager == null) {
    threadManager=new ThreadManager();
  }
  return threadManager;
}
