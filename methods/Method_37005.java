/** 
 * append new cards into adapter
 * @param cards new cards will be added to the end
 */
public void appendGroup(@Nullable List<L> cards){
  if (cards == null || cards.size() == 0)   return;
  createSnapshot();
  final List<LayoutHelper> helpers=new LinkedList<>(getLayoutHelpers());
  mCards.ensureCapacity(mCards.size() + cards.size());
  helpers.addAll(transformCards(cards,mData,mCards));
  setLayoutHelpers(helpers);
  diffWithSnapshot();
  notifyDataSetChanged();
}
