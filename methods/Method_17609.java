private void demoteProtected(){
  if (sizeProtected > maxProtected) {
    Node demote=headProtected.next;
    demote.remove();
    demote.status=Status.PROBATION;
    demote.appendToTail(headProbation);
    sizeProtected--;
  }
}
