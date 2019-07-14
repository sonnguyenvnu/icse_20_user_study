@Override public void addChildCard(Card card){
  if (card == null) {
    return;
  }
  List<BaseCell> subCells=card.getCells();
  if (subCells != null && !subCells.isEmpty()) {
    addCells(card.getCells());
    int startOffset=mCells.indexOf(subCells.get(0));
    int endOffset=mCells.indexOf(subCells.get(subCells.size() - 1));
    Range range=Range.create(startOffset,endOffset);
    mChildren.put(range,card);
  }
}
