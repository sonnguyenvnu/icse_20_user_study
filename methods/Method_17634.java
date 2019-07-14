private void adjust(){
  int maxPerLevel=maxMain / levels;
  for (int i=levels - 1; i > 0; i--) {
    if (sizeMainQ[i] > maxPerLevel) {
      Node demote=headMainQ[i].next;
      demote.remove();
      sizeMainQ[i]--;
      demote.level=i - 1;
      sizeMainQ[demote.level]++;
      demote.appendToTail(headMainQ[demote.level]);
    }
  }
}
