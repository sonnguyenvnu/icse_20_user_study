/** 
 * remove a group
 * @param group the group to be removed
 */
public void removeGroup(@Nullable L group){
  if (group == null) {
    return;
  }
  List<L> cards=getGroups();
  boolean changed=cards.remove(group);
  if (changed) {
    setData(cards);
  }
}
