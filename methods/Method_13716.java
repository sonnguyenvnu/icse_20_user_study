public void reset(Set<MessageQueue> queueSet){
  this.messageQueues=null;
  this.messageQueues=new ArrayList<>(queueSet);
  this.queueIndex=0;
}
