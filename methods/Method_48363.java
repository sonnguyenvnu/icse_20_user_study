/** 
 * This method should be called by  {@link org.janusgraph.diskstorage.log.Log} implementations when the message was successfullyadded to the log.
 */
public void delivered(){
  super.set(message);
}
