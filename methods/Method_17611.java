private boolean adapt(Node candidate){
  if (adjusted == sampled) {
    return false;
  }
  if (feedback.mightContain(candidate.key)) {
    adjusted=sampled;
    if (pivot < maxPivot) {
      pivot++;
      maxWindow++;
      sizeWindow++;
      maxProtected--;
      demoteProtected();
      candidate.remove();
      candidate.status=Status.WINDOW;
      candidate.appendToTail(headWindow);
      int increments=Math.min(pivotIncrement - 1,maxPivot - pivot);
      for (int i=0; i < increments; i++) {
        if (pivot < maxPivot) {
          pivot++;
          maxWindow++;
          sizeWindow++;
          maxProtected--;
          demoteProtected();
          candidate=headProbation.next.next;
          candidate.remove();
          candidate.status=Status.WINDOW;
          candidate.appendToTail(headWindow);
        }
      }
      if (trace) {
        System.out.println("?" + maxWindow);
      }
    }
    return true;
  }
 else   if (sampled > (adjusted + 1)) {
    adjusted=sampled;
    boolean decremented=false;
    for (int i=0; i < pivotDecrement; i++) {
      if (pivot > 0) {
        pivot--;
        maxWindow--;
        sizeWindow--;
        maxProtected++;
        decremented=true;
        candidate=headWindow.next;
        candidate.remove();
        candidate.status=Status.PROBATION;
        candidate.appendToHead(headProbation);
      }
    }
    if (trace && decremented) {
      System.out.println("?" + maxWindow);
    }
  }
  return false;
}
