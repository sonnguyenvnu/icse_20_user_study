private void demoteProtected(){
  if (protectedSize > maxProtected) {
    Node demote=headProtected.next;
    demote.remove();
    demote.queue=PROBATION;
    demote.appendToTail(headProbation);
    protectedSize--;
  }
}
