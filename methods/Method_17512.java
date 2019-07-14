private void adjust(){
  int maxPerLevel=maximumSize / levels;
  for (int i=levels - 1; i > 0; i--) {
    if (sizeQ[i] > maxPerLevel) {
      Node demote=headQ[i].next;
      demote.remove();
      sizeQ[i]--;
      demote.level=i - 1;
      sizeQ[demote.level]++;
      demote.appendToTail(headQ[demote.level]);
    }
  }
}
