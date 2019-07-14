public synchronized void resetMessageQueues(Set<MessageQueue> queueSet){
  log.info("resetMessageQueues, topic='{}', messageQueue=`{}`",topic,queueSet);
synchronized (this.consumerMonitor) {
    this.messageQueueChooser.reset(queueSet);
  }
}
