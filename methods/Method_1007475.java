private BasicBlock dequeue(){
  BasicBlock bb=workset.poll();
  bb.unsetFlag(ENQUEUED);
  return bb;
}
