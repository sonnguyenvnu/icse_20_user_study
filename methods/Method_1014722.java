public static void remove(){
  Thread thread=Thread.currentThread();
  if (thread instanceof InternalThread) {
    ((InternalThread)thread).setThreadLocalMap(null);
  }
 else {
    slowThreadLocalMap.remove();
  }
}
