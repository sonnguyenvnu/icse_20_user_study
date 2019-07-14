/** 
 * Return true if this port is still active and hasn't run into any trouble.
 */
public boolean active(){
  return port.isOpened();
}
