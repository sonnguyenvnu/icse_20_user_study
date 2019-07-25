/** 
 * Inspects if the barrier is broken. If for any reason, the barrier was broken, a  {@link BrokenBarrierException} will be thrown. Otherwise,would return gracefully.
 * @throws BrokenBarrierException With a nested broken cause.
 */
public synchronized void inspect() throws BrokenBarrierException {
  try {
    breakIfBroken();
  }
 catch (  BrokenBarrierException bbe) {
    initCause(bbe);
    throw bbe;
  }
}
