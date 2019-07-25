/** 
 * Create a Bukkit ItemStack from a WorldEdit BaseItemStack
 * @param item The WorldEdit BaseItemStack
 * @return The Bukkit ItemStack
 */
public static ItemStack adapt(BaseItemStack item){
  checkNotNull(item);
  if (WorldEditPlugin.getInstance().getBukkitImplAdapter() != null) {
    return WorldEditPlugin.getInstance().getBukkitImplAdapter().adapt(item);
  }
  return new ItemStack(adapt(item.getType()),item.getAmount());
}
