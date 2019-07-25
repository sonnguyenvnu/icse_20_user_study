/** 
 * Removes an item and it´s member if recursive flag is set to true.
 * @param itemName item name to remove
 * @param recursive if set to true all members of the item will be removed, too.
 * @return removed item or null if no item with that name exists
 */
public Item remove(String itemName,boolean recursive){
  Item item=get(itemName);
  if (recursive && item instanceof GroupItem) {
    for (    String member : getMemberNamesRecursively((GroupItem)item,getAll())) {
      this.remove(member);
    }
  }
  if (item != null) {
    this.remove(item.getName());
    return item;
  }
 else {
    return null;
  }
}
