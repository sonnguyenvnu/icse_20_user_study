/** 
 * change universe id
 * @param universeId new universe id
 */
public void rename(int universeId){
  logger.debug("Renaming universe {} to {}",this.universeId,universeId);
  this.universeId=universeId;
  for (  DmxChannel channel : channels) {
    channel.setUniverseId(universeId);
  }
}
