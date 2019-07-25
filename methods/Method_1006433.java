@Override public MappeableContainer inot(int rangeStart,int rangeEnd){
  if (rangeEnd <= rangeStart) {
    return this;
  }
  short[] vl=this.valueslength.array();
  if (vl.length <= 2 * nbrruns + 1) {
    boolean lastValueBeforeRange=false;
    boolean firstValueInRange=false;
    boolean lastValueInRange=false;
    boolean firstValuePastRange=false;
    if (rangeStart > 0) {
      lastValueBeforeRange=contains((short)(rangeStart - 1));
    }
    firstValueInRange=contains((short)rangeStart);
    if (lastValueBeforeRange == firstValueInRange) {
      lastValueInRange=contains((short)(rangeEnd - 1));
      if (rangeEnd != 65536) {
        firstValuePastRange=contains((short)rangeEnd);
      }
      if (lastValueInRange == firstValuePastRange) {
        return not(rangeStart,rangeEnd);
      }
    }
  }
  int myNbrRuns=nbrruns;
  MappeableRunContainer ans=this;
  int k=0;
  ans.nbrruns=0;
  for (; k < myNbrRuns && toIntUnsigned(this.getValue(k)) < rangeStart; ++k) {
    ans.nbrruns++;
  }
  short bufferedValue=0, bufferedLength=0;
  short nextValue=0, nextLength=0;
  if (k < myNbrRuns) {
    bufferedValue=vl[2 * k];
    bufferedLength=vl[2 * k + 1];
  }
  ans.smartAppendExclusive(vl,(short)rangeStart,(short)(rangeEnd - rangeStart - 1));
  for (; k < myNbrRuns; ++k) {
    if (ans.nbrruns > k + 1) {
      throw new RuntimeException("internal error in inot, writer has overtaken reader!! " + k + " " + ans.nbrruns);
    }
    if (k + 1 < myNbrRuns) {
      nextValue=vl[2 * (k + 1)];
      nextLength=vl[2 * (k + 1) + 1];
    }
    ans.smartAppendExclusive(vl,bufferedValue,bufferedLength);
    bufferedValue=nextValue;
    bufferedLength=nextLength;
  }
  return ans.toEfficientContainer();
}
