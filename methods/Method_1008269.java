/** 
 * Returns <code>true</code> iff the this shard is currently {@link ShardRoutingState#STARTED started} or{@link ShardRoutingState#RELOCATING relocating} to another node.Otherwise <code>false</code>
 */
public boolean active(){
  return started() || relocating();
}
