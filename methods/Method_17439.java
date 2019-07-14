@Override public void finished(){
  checkState(sizeT1 == data.values().stream().filter(node -> node.type == QueueType.T1).count());
  checkState(sizeT2 == data.values().stream().filter(node -> node.type == QueueType.T2).count());
  checkState(sizeB1 == data.values().stream().filter(node -> node.type == QueueType.B1).count());
  checkState(sizeB2 == data.values().stream().filter(node -> node.type == QueueType.B2).count());
  checkState((sizeT1 + sizeT2) <= maximumSize);
  checkState((sizeB1 + sizeB2) <= maximumSize);
}
