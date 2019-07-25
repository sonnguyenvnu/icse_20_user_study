@Override public synchronized Result get(String key){
  long timestamp=timeGen();
  if (timestamp < lastTimestamp) {
    long offset=lastTimestamp - timestamp;
    if (offset <= 5) {
      try {
        wait(offset << 1);
        timestamp=timeGen();
        if (timestamp < lastTimestamp) {
          return new Result(-1,Status.EXCEPTION);
        }
      }
 catch (      InterruptedException e) {
        LOGGER.error("wait interrupted");
        return new Result(-2,Status.EXCEPTION);
      }
    }
 else {
      return new Result(-3,Status.EXCEPTION);
    }
  }
  if (lastTimestamp == timestamp) {
    sequence=(sequence + 1) & sequenceMask;
    if (sequence == 0) {
      sequence=RANDOM.nextInt(100);
      timestamp=tilNextMillis(lastTimestamp);
    }
  }
 else {
    sequence=RANDOM.nextInt(100);
  }
  lastTimestamp=timestamp;
  long id=((timestamp - twepoch) << timestampLeftShift) | (workerId << workerIdShift) | sequence;
  return new Result(id,Status.SUCCESS);
}
