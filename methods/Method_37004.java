/** 
 * @param cards   new cards data
 * @param silence true, call {@link RecyclerView.Adapter#notifyDataSetChanged()}; false do not call {@link RecyclerView.Adapter#notifyDataSetChanged()}
 */
public void setData(@Nullable List<L> cards,boolean silence){
  createSnapshot();
  mCards.clear();
  mData.clear();
  if (cards != null && cards.size() != 0) {
    mCards.ensureCapacity(cards.size());
    setLayoutHelpers(transformCards(cards,mData,mCards));
  }
 else {
    setLayoutHelpers(Collections.<LayoutHelper>emptyList());
  }
  diffWithSnapshot();
  if (!silence)   notifyDataSetChanged();
}
