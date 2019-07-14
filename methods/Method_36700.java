/** 
 * @return total card list
 */
public List<L> getGroups(){
  List<L> cards=new ArrayList<>(mCards.size());
  for (int i=0, size=mCards.size(); i < size; i++) {
    Pair<Range<Integer>,L> pair=mCards.get(i);
    cards.add(pair.second);
  }
  return cards;
}
