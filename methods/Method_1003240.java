private static void sleep(int time){
  try {
    Thread.sleep(time);
  }
 catch (  InterruptedException e) {
    throw getExceptionFatal("Sleep interrupted",e);
  }
}
