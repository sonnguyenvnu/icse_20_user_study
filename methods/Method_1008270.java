/** 
 * Returns <code>true</code> iff the this shard is currently relocating to another node. Otherwise <code>false</code>
 * @see ShardRoutingState#RELOCATING
 */
public boolean relocating(){
  return state == ShardRoutingState.RELOCATING;
}
