@Override public State clone(){
  State state=new State(arcs.length - 1);
  state.stack=new ArrayDeque<Integer>(stack);
  for (int dependent=0; dependent < arcs.length; dependent++) {
    if (arcs[dependent] != null) {
      Edge head=arcs[dependent];
      state.arcs[dependent]=head;
      int h=head.headIndex;
      if (rightMostArcs[h] != 0) {
        state.rightMostArcs[h]=rightMostArcs[h];
        state.rightValency[h]=rightValency[h];
        state.rightDepLabels[h]=rightDepLabels[h];
      }
      if (leftMostArcs[h] != 0) {
        state.leftMostArcs[h]=leftMostArcs[h];
        state.leftValency[h]=leftValency[h];
        state.leftDepLabels[h]=leftDepLabels[h];
      }
    }
  }
  state.rootIndex=rootIndex;
  state.bufferHead=bufferHead;
  state.maxSentenceSize=maxSentenceSize;
  state.emptyFlag=emptyFlag;
  return state;
}
