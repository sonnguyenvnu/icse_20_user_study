/** 
 * Remove all cells in a card.
 * @param group
 * @since 2.1.0
 */
public void removeBatchBy(Card group){
  VirtualLayoutManager layoutManager=getLayoutManager();
  if (group != null && mGroupBasicAdapter != null && layoutManager != null) {
    int cardIdx=mGroupBasicAdapter.findCardIdxForCard(group);
    List<LayoutHelper> layoutHelpers=layoutManager.getLayoutHelpers();
    LayoutHelper emptyLayoutHelper=null;
    int removeItemCount=0;
    if (layoutHelpers != null && cardIdx >= 0 && cardIdx < layoutHelpers.size()) {
      for (int i=0, size=layoutHelpers.size(); i < size; i++) {
        LayoutHelper layoutHelper=layoutHelpers.get(i);
        int start=layoutHelper.getRange().getLower();
        int end=layoutHelper.getRange().getUpper();
        if (i < cardIdx) {
        }
 else         if (i == cardIdx) {
          removeItemCount=layoutHelper.getItemCount();
          emptyLayoutHelper=layoutHelper;
        }
 else {
          layoutHelper.setRange(start - removeItemCount,end - removeItemCount);
        }
      }
      if (emptyLayoutHelper != null) {
        final List<LayoutHelper> newLayoutHelpers=new LinkedList<>(layoutHelpers);
        newLayoutHelpers.remove(emptyLayoutHelper);
        layoutManager.setLayoutHelpers(newLayoutHelpers);
      }
      mGroupBasicAdapter.removeComponents(group);
    }
  }
}
