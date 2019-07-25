@Override public int fill(Supplier<E> s,int limit){
  final int positionOnCycleMask=this.positionWithinCycleMask;
  final int cycleLengthLog2=this.cycleLengthLog2;
  final int cycleLength=this.cycleLength;
  final int cycleIdBitShift=this.cycleIdBitShift;
  final E[] buffer=this.buffer;
  final long maxCycleId=this.maxCycleId;
  final long maxPositionOnCycle=positionOnCycleMask;
  int i=0;
  while (i < limit) {
    final int activeCycle=activeCycleIndex(lvActiveCycleId());
    final long producerActiveCycleClaim=lvProducerCycleClaim(activeCycle);
    final int positionOnActiveCycle=positionWithinCycle(producerActiveCycleClaim,positionOnCycleMask);
    final long activeCycleId=producerClaimCycleId(producerActiveCycleClaim,cycleIdBitShift);
    final long producerPosition=producerPosition(positionOnActiveCycle,activeCycleId,cycleLengthLog2);
    final long claimLimit=lvProducerLimit();
    if (producerPosition >= claimLimit) {
      if (isFull(producerPosition)) {
        return i;
      }
    }
    final long producerCycleClaim=getAndIncrementProducerCycleClaim(activeCycle);
    final int positionOnCycle=positionWithinCycle(producerCycleClaim,positionOnCycleMask);
    if (positionOnCycle >= maxPositionOnCycle) {
      throw new IllegalStateException("too many over-claims: please enlarge the capacity or reduce the number of producers!");
    }
    if (positionOnCycle < cycleLength) {
      final long cycleId=producerClaimCycleId(producerCycleClaim,cycleIdBitShift);
      final boolean slowProducer=cycleId != activeCycleId;
      if (!validateProducerClaim(activeCycle,producerCycleClaim,cycleId,positionOnCycle,cycleLengthLog2,slowProducer)) {
        continue;
      }
      soCycleElement(buffer,s.get(),activeCycle,positionOnCycle,cycleLengthLog2);
      i++;
    }
 else     if (positionOnCycle == cycleLength) {
      final long cycleId=producerClaimCycleId(producerCycleClaim,cycleIdBitShift);
      rotateCycle(cycleId,cycleIdBitShift,maxCycleId);
    }
  }
  return i;
}
