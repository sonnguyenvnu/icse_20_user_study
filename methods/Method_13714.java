public int requeue(){
  if (queueIndex - 1 < 0) {
    this.queueIndex=messageQueues.size() - 1;
  }
 else {
    this.queueIndex=this.queueIndex - 1;
  }
  return this.queueIndex;
}
