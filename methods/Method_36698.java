/** 
 * insert groups after position inserted
 * @param inserted index that will inserted after
 * @param groups   new groups
 */
public void insertGroup(int inserted,@Nullable List<L> groups){
  if (inserted < 0 || inserted >= mCards.size() || groups == null || groups.size() == 0)   return;
  List<L> cards=getGroups();
  boolean changed=cards.addAll(inserted,groups);
  if (changed)   setData(cards);
}
