public static ItemType adapt(Item item){
  return ItemTypes.get(Registry.ITEM.getId(item).toString());
}
