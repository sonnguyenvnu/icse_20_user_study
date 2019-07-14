private void decreaseWindow(double amount){
  checkState(amount >= 0.0);
  if (maxWindow == 0) {
    return;
  }
  double quota=Math.min(amount,maxWindow);
  int steps=(int)windowSize - (int)(windowSize - quota);
  windowSize-=quota;
  for (int i=0; i < steps; i++) {
    maxWindow--;
    maxProtected++;
    Node candidate=headWindow.next;
    candidate.remove();
    candidate.queue=PROBATION;
    candidate.appendToHead(headProbation);
  }
  checkState(windowSize >= 0);
  checkState(maxWindow >= 0);
  checkState(maxProtected >= 0);
  if (trace) {
    System.out.printf("-%,d (%,d -> %,d)%n",steps,maxWindow + steps,maxWindow);
  }
}
