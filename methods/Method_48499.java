@Override public void getPartitions(Map<InternalVertex,PartitionAssignment> vertices){
  int partitionID=nextPartitionID();
  for (  Map.Entry<InternalVertex,PartitionAssignment> entry : vertices.entrySet()) {
    entry.setValue(new SimplePartitionAssignment(partitionID));
  }
}
