public void clearPolling(String queueName){
synchronized (lockObj) {
    sPollingMap.put(queueName,false);
    lockObj.notifyAll();
    if (debugLogOpen) {
      log.info("PullMessageTask_WakeUp:Everyone WakeUp and Work!");
    }
  }
}
