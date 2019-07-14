@Override public synchronized void release(Allocation[] allocations){
  if (availableCount + allocations.length >= availableAllocations.length) {
    availableAllocations=Arrays.copyOf(availableAllocations,Math.max(availableAllocations.length * 2,availableCount + allocations.length));
  }
  for (  Allocation allocation : allocations) {
    availableAllocations[availableCount++]=allocation;
  }
  allocatedCount-=allocations.length;
  notifyAll();
}
