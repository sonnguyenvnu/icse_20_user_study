@Override public void exhaustedPartition(int partitionID){
  exhaustedPartitions.add(partitionID);
  for (int i=0; i < currentPartitions.length; i++) {
    if (currentPartitions[i] == partitionID) {
      updateElement(i);
    }
  }
}
