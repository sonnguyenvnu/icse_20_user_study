/** 
 * Clears the queue and discards new requests from now on 
 */
public void suspend(){
  if (suspended.compareAndSet(false,true))   requests.clear();
}
