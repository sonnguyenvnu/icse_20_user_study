private long tilNextMillis(){
  long newTime=System.currentTimeMillis();
  while (newTime <= lastTime) {
    newTime=System.currentTimeMillis();
  }
  return newTime;
}
