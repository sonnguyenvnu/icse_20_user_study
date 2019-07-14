/** 
 * Get correspond items for card
 * @param card the card look items for
 * @return cells of this card
 */
@Override public List<BaseCell> getItems(@NonNull Card card){
  if (card.style != null && !TextUtils.isEmpty(card.style.forLabel)) {
    String forId=card.style.forLabel;
    if (mIdCardCache.containsKey(forId)) {
      Card forCard=mIdCardCache.get(forId);
      if (forCard.mCells.size() == 0) {
        if (TextUtils.isEmpty(forCard.load)) {
          return null;
        }
 else {
          return Collections.emptyList();
        }
      }
    }
  }
  if (TextUtils.isEmpty(card.load) && card.mCells.isEmpty()) {
    return null;
  }
  return new LinkedList<>(card.mCells);
}
