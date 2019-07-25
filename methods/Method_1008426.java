/** 
 * Resets the barrier to its initial state.  If any parties are currently waiting at the barrier, they will return with a {@link BrokenBarrierException}. Note that resets <em>after</em> a breakage has occurred for other reasons can be complicated to carry out; threads need to re-synchronize in some other way, and choose one to perform the reset.  It may be preferable to instead create a new barrier for subsequent use.
 * @param cause The cause of the BrokenBarrierException
 */
public synchronized void reset(Exception cause){
  if (!isBroken()) {
    super.reset();
  }
  if (this.cause == null) {
    this.cause=cause;
  }
}
