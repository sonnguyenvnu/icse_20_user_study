/** 
 * Create a WorldEdit GameMode from a Bukkit one.
 * @param gameMode Bukkit GameMode
 * @return WorldEdit GameMode
 */
public static GameMode adapt(org.bukkit.GameMode gameMode){
  checkNotNull(gameMode);
  return GameModes.get(gameMode.name().toLowerCase(Locale.ROOT));
}
