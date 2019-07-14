public static void terminate(Thread[] threads){
  for (  Thread thread : threads) {
    if (thread != null && thread.isAlive())     thread.interrupt();
  }
}
