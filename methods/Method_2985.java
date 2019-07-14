public void addArc(int dependent,int head,int dependency){
  arcs[dependent]=new Edge(head,dependency);
  long value=1L << (dependency);
  assert dependency < 64;
  if (dependent > head) {
    if (rightMostArcs[head] == 0 || dependent > rightMostArcs[head])     rightMostArcs[head]=dependent;
    rightValency[head]+=1;
    rightDepLabels[head]=rightDepLabels[head] | value;
  }
 else {
    if (leftMostArcs[head] == 0 || dependent < leftMostArcs[head])     leftMostArcs[head]=dependent;
    leftDepLabels[head]=leftDepLabels[head] | value;
    leftValency[head]+=1;
  }
}
