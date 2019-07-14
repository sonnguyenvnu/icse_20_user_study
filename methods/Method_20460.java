/** 
 * Holding a read lock to avoid returning true while the pool is being recycled, this returns true if the pool has at least one decoder available.
 */
@Override public synchronized boolean isReady(){
  return decoderPool != null && !decoderPool.isEmpty();
}
