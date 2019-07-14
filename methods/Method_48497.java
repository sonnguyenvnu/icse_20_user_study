private void updateElement(int index){
  Preconditions.checkArgument(localPartitionIdRanges != null && !localPartitionIdRanges.isEmpty(),"Local partition id ranges have not been initialized");
  int newPartition;
  int attempts=0;
  do {
    attempts++;
    newPartition=localPartitionIdRanges.get(random.nextInt(localPartitionIdRanges.size())).getRandomID();
    if (attempts > PARTITION_FINDING_ATTEMPTS)     throw new IDPoolExhaustedException("Could not find non-exhausted partition");
  }
 while (exhaustedPartitions.contains(newPartition));
  currentPartitions[index]=newPartition;
  log.debug("Setting partition at index [{}] to: {}",index,newPartition);
}
