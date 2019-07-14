public static boolean oneAlive(Thread[] threads){
  for (  Thread thread : threads) {
    if (thread != null && thread.isAlive())     return true;
  }
  return false;
}
