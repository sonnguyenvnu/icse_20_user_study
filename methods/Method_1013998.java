@Override public Item build(){
  Item item=null;
  if (GroupItem.TYPE.equals(itemType)) {
    item=new GroupItem(name,baseItem,groupFunction);
  }
 else {
    for (    ItemFactory itemFactory : itemFactories) {
      item=itemFactory.createItem(itemType,name);
      if (item != null) {
        break;
      }
    }
  }
  if (item == null) {
    throw new IllegalStateException("No item factory could handle type " + itemType);
  }
  if (item instanceof ActiveItem) {
    ActiveItem activeItem=(ActiveItem)item;
    activeItem.setLabel(label);
    activeItem.setCategory(category);
    activeItem.addGroupNames(groups);
    activeItem.addTags(tags);
  }
  return item;
}
