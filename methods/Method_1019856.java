/** 
 * <p>Get the event for a given sequence in the RingBuffer.</p> <p>This call has 2 uses.  Firstly use this call when publishing to a ring buffer. After calling  {@link RingBuffer#next()} use this call to get hold of thepreallocated event to fill with data before calling  {@link RingBuffer#publish(long)}.</p> <p>Secondly use this call when consuming data from the ring buffer.  After calling {@link SequenceBarrier#waitFor(long)} call this method with any value greater thanthat your current consumer sequence and less than or equal to the value returned from the  {@link SequenceBarrier#waitFor(long)} method.</p>
 * @param sequence for the event
 * @return the event for the given sequence
 */
@Override public E get(long sequence){
  return elementAt(sequence);
}
