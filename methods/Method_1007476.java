void enqueue(BasicBlock bb){
  assert bb.startFrame != null : "Enqueued null start frame";
  if (!bb.hasFlag(ENQUEUED)) {
    workset.add(bb);
    bb.setFlag(ENQUEUED);
  }
}
