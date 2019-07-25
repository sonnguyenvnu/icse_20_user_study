/** 
 * Set the state of the Console publisher.
 * @param enabled True if metrics are published to the console. False otherwise
 * @return {@code this}
 */
public ConsoleConfig enable(boolean enabled){
  this.enabled=enabled;
  return this;
}
