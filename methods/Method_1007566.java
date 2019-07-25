/** 
 * Create a WorldEdit Player from a Bukkit Player.
 * @param player The Bukkit player
 * @return The WorldEdit player
 */
public static BukkitPlayer adapt(Player player){
  return WorldEditPlugin.getInstance().wrapPlayer(player);
}
