/** 
 * Set the state of the CSV publisher.
 * @param enabled True if metrics are published to CSV. False otherwise
 * @return this
 */
public CsvConfig enable(boolean enabled){
  this.enabled=enabled;
  return this;
}
