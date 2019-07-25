/** 
 * @return A {@code Collection} of all played card lists.
 */
public synchronized Collection<List<WhiteCard>> cards(){
  return playerCardMap.values();
}
