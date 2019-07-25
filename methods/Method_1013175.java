/** 
 * @param upperBound The upper bound for the thread to sleep
 * @return The actual sleep time in seconds
 */
protected long sleep(){
  long seconds=rnd.nextInt(sleepUpperBound);
  try {
    Thread.sleep((seconds + 1) * 1000);
  }
 catch (  InterruptedException e) {
    e.printStackTrace();
  }
  return seconds;
}
