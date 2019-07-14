/** 
 * replace group in position replaced with new groups
 * @param replaced index that will be replaced
 * @param groups   new groups
 */
public void replaceGroup(int replaced,@Nullable List<L> groups){
  if (replaced < 0 || replaced >= mCards.size() || groups == null || groups.size() == 0)   return;
  List<L> cards=getGroups();
  boolean changed=cards.addAll(replaced + 1,groups);
  if (changed) {
    cards.remove(replaced);
    setData(cards);
  }
}
