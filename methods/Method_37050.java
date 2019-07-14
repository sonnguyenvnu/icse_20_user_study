protected void parseFooterCell(BaseCell footerCell,Card card){
  card.mFooter=footerCell;
  if (card instanceof GridCard) {
    GridCard gridCard=(GridCard)card;
    gridCard.ensureBlock(card.mFooter);
  }
 else   if (card instanceof OnePlusNCard) {
    OnePlusNCard onePlusNCard=(OnePlusNCard)card;
    onePlusNCard.ensureBlock(card.mFooter);
  }
}
