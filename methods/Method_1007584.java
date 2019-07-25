/** 
 * Create a WorldEdit BaseItemStack from a Bukkit ItemStack
 * @param itemStack The Bukkit ItemStack
 * @return The WorldEdit BaseItemStack
 */
public static BaseItemStack adapt(ItemStack itemStack){
  checkNotNull(itemStack);
  if (WorldEditPlugin.getInstance().getBukkitImplAdapter() != null) {
    return WorldEditPlugin.getInstance().getBukkitImplAdapter().adapt(itemStack);
  }
  return new BaseItemStack(ItemTypes.get(itemStack.getType().getKey().toString()),itemStack.getAmount());
}
