/** 
 * Remove target cell. TODO handle nested card, cell in staggered, cell in onePlusN
 * @param data
 * @since 2.1.0
 */
public void removeBy(BaseCell data){
  VirtualLayoutManager layoutManager=getLayoutManager();
  if (data != null && mGroupBasicAdapter != null && layoutManager != null) {
    int removePosition=mGroupBasicAdapter.getPositionByItem(data);
    if (removePosition >= 0) {
      int cardIdx=mGroupBasicAdapter.findCardIdxFor(removePosition);
      Card card=mGroupBasicAdapter.getCardRange(cardIdx).second;
      card.removeCellSilently(data);
      List<LayoutHelper> layoutHelpers=layoutManager.getLayoutHelpers();
      LayoutHelper emptyLayoutHelper=null;
      if (layoutHelpers != null && cardIdx >= 0 && cardIdx < layoutHelpers.size()) {
        for (int i=0, size=layoutHelpers.size(); i < size; i++) {
          LayoutHelper layoutHelper=layoutHelpers.get(i);
          int start=layoutHelper.getRange().getLower();
          int end=layoutHelper.getRange().getUpper();
          if (end < removePosition) {
          }
 else           if (start <= removePosition && removePosition <= end) {
            int itemCount=layoutHelper.getItemCount() - 1;
            if (itemCount > 0) {
              layoutHelper.setItemCount(itemCount);
              layoutHelper.setRange(start,end - 1);
            }
 else {
              emptyLayoutHelper=layoutHelper;
            }
          }
 else           if (removePosition < start) {
            layoutHelper.setRange(start - 1,end - 1);
          }
        }
        if (emptyLayoutHelper != null) {
          final List<LayoutHelper> newLayoutHelpers=new LinkedList<>(layoutHelpers);
          newLayoutHelpers.remove(emptyLayoutHelper);
          layoutManager.setLayoutHelpers(newLayoutHelpers);
        }
        mGroupBasicAdapter.removeComponent(data);
      }
    }
  }
}
