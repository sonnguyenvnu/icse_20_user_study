/** 
 * @see Sequencer#publish(long)
 */
@Override public void publish(long sequence){
  cursor.set(sequence);
  waitStrategy.signalAllWhenBlocking();
}
