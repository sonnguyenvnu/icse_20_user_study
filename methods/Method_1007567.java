/** 
 * Create a Bukkit Player from a WorldEdit Player.
 * @param player The WorldEdit player
 * @return The Bukkit player
 */
public static Player adapt(com.sk89q.worldedit.entity.Player player){
  return ((BukkitPlayer)player).getPlayer();
}
