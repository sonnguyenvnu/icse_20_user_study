/** 
 * @see Sequencer#publish(long)
 */
@Override public void publish(final long sequence){
  setAvailable(sequence);
  waitStrategy.signalAllWhenBlocking();
}
