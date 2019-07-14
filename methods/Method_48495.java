@Override public void getPartitions(Map<InternalVertex,PartitionAssignment> vertices){
  super.getPartitions(vertices);
  for (  Map.Entry<InternalVertex,PartitionAssignment> entry : vertices.entrySet()) {
    int pid=getPartitionIDbyKey(entry.getKey());
    if (pid >= 0)     ((SimplePartitionAssignment)entry.getValue()).setPartitionID(pid);
  }
}
