/** 
 * A high performance method to insert cells. Do not allowed to insert to an empty Tangram. TODO handle nested card
 * @param insertPosition the position to be inserted
 * @param list new cell data list
 * @since 2.1.0
 */
public void insertWith(int insertPosition,List<BaseCell> list){
  int newItemSize=list != null ? list.size() : 0;
  if (newItemSize > 0 && mGroupBasicAdapter != null) {
    if (insertPosition >= mGroupBasicAdapter.getItemCount()) {
      insertPosition=mGroupBasicAdapter.getItemCount() - 1;
    }
    BaseCell insertCell=mGroupBasicAdapter.getItemByPosition(insertPosition);
    int cardIdx=mGroupBasicAdapter.findCardIdxFor(insertPosition);
    Card card=mGroupBasicAdapter.getCardRange(cardIdx).second;
    card.addCells(card,card.getCells().indexOf(insertCell),list);
    List<LayoutHelper> layoutHelpers=getLayoutManager().getLayoutHelpers();
    if (layoutHelpers != null && cardIdx >= 0 && cardIdx < layoutHelpers.size()) {
      for (int i=0, size=layoutHelpers.size(); i < size; i++) {
        LayoutHelper layoutHelper=layoutHelpers.get(i);
        int start=layoutHelper.getRange().getLower();
        int end=layoutHelper.getRange().getUpper();
        if (end < insertPosition) {
        }
 else         if (start <= insertPosition && insertPosition <= end) {
          layoutHelper.setItemCount(layoutHelper.getItemCount() + newItemSize);
          layoutHelper.setRange(start,end + newItemSize);
        }
 else         if (insertPosition < start) {
          layoutHelper.setRange(start + newItemSize,end + newItemSize);
        }
      }
      mGroupBasicAdapter.insertComponents(insertPosition,list);
    }
  }
}
