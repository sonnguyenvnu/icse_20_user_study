public static Item adapt(ItemType itemType){
  return Registry.ITEM.get(new Identifier(itemType.getId()));
}
