public void updateCard(Card card){
  if (card != null && mGroupBasicAdapter != null) {
    Range<Integer> cardRange=mGroupBasicAdapter.getCardRange(card);
    for (int i=cardRange.getLower(); i < cardRange.getUpper(); i++) {
      BaseCell cell=mGroupBasicAdapter.getItemByPosition(i);
      cell.extras.put("_flag_invalidate_",true);
    }
    mGroupBasicAdapter.notifyItemRangeChanged(cardRange.getLower(),cardRange.getUpper());
  }
}
