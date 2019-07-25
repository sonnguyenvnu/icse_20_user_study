@Override public int size(){
  final int positionOnCycleMask=this.positionOnCycleMask;
  final int cycleLength=this.cycleLength;
  final AtomicLongArray producerCycleClaim=this.producerCycleClaim;
  long after=lvConsumerPosition();
  int positionOnCycle;
  long producerClaim;
  long before;
  do {
    before=after;
    final int activeClaim=activeCycleIndex(lvActiveCycleId());
    producerClaim=AtomicLongArrayAccess.lvValue(producerCycleClaim,activeClaim);
    after=lvConsumerPosition();
    positionOnCycle=positionOnCycle(producerClaim,positionOnCycleMask);
  }
 while (positionOnCycle > cycleLength || before != after);
  final long size=producerPosition(cycleId(producerClaim,this.cycleIdBitShift),positionOnCycle,this.cycleLengthLog2) - after;
  if (size > Integer.MAX_VALUE) {
    return Integer.MAX_VALUE;
  }
 else {
    return (int)size;
  }
}
