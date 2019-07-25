public static ItemType adapt(Item item){
  return ItemTypes.get(ForgeRegistries.ITEMS.getKey(item).toString());
}
