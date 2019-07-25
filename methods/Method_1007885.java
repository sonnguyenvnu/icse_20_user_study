/** 
 * Create a WorldEdit Player from a Sponge Player.
 * @param player The Sponge player
 * @return The WorldEdit player
 */
public static SpongePlayer adapt(Player player){
  return SpongeWorldEdit.inst().wrapPlayer(player);
}
