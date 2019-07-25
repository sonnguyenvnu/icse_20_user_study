/** 
 * @see Sequencer#publish(long,long)
 */
@Override public void publish(long lo,long hi){
  for (long l=lo; l <= hi; l++) {
    setAvailable(l);
  }
  waitStrategy.signalAllWhenBlocking();
}
