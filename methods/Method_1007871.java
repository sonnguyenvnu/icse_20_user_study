public static Item adapt(ItemType itemType){
  return ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemType.getId()));
}
