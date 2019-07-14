protected void parseHeaderCell(BaseCell headerCell,Card card){
  card.mHeader=headerCell;
  if (card instanceof GridCard) {
    GridCard gridCard=(GridCard)card;
    gridCard.ensureBlock(card.mHeader);
  }
 else   if (card instanceof OnePlusNCard) {
    OnePlusNCard onePlusNCard=(OnePlusNCard)card;
    onePlusNCard.ensureBlock(card.mHeader);
  }
}
