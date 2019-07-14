public int fixPosition(int position){
  if (hasHints) {
    position-=2 + MessagesController.getInstance(currentAccount).hintDialogs.size();
  }
  if (showArchiveHint) {
    position-=2;
  }
  return position;
}
